package controllers;

import java.util.ArrayList;
import java.util.List;

import models.NS;

import org.codehaus.jackson.node.ObjectNode;
import org.openrdf.query.MalformedQueryException;
import org.openrdf.query.QueryEvaluationException;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.object.ObjectConnection;
import org.openrdf.repository.object.ObjectQuery;

import global.Sesame;
import play.data.DynamicForm;
import play.mvc.Result;
import play.mvc.Controller;

public class Game extends Controller {

	public static final String progressHuntSessionKey = "progressHunt";
	public static final String progressStepSessionKey = "progressStep";
	public static final String progressEnigmaSessionKey = "progressEnigma";
	public static final String progressClueSessionKey = "progressClue";

	public static Result home() throws MalformedQueryException, RepositoryException, QueryEvaluationException {
//		List<models.Hunt> hunts = models.Hunt.getHuntsSortedByDate();
		
		ObjectConnection oc = Sesame.getObjectConnection();
		ObjectQuery query = oc.prepareObjectQuery(NS.PREFIX + 
				"SELECT ?hunt WHERE { ?hunt gngm:modifiedAt ?date } ORDER BY DESC(?date) LIMIT 20");

		List<models.Hunt> hunts = query.evaluate(models.Hunt.class).asList();
		
		return ok(views.html.game.home.render(hunts));
	}

	public static Result playHunt(String hid) throws RepositoryException, QueryEvaluationException {
		ObjectConnection oc = Sesame.getObjectConnection();
		models.Hunt hunt = oc.getObject(models.Hunt.class, models.Hunt.URI + hid);
		
		session().put(progressHuntSessionKey, hunt.getId());
		try {
			session().put(progressStepSessionKey, hunt.getSteps().get(0).getId());
		} catch (IndexOutOfBoundsException e) {
			session().put(progressStepSessionKey, "");
		}
		
		session().put(progressEnigmaSessionKey, "");
		session().put(progressClueSessionKey, "");
		
		return ok(views.html.game.playHunt.render(hunt));
	}

	public static Result startStep() throws RepositoryException, QueryEvaluationException {
		ObjectConnection oc = Sesame.getObjectConnection();

		models.Step currentStep = oc.getObject(models.Step.class, models.Step.URI + session(progressStepSessionKey));

		if (session(progressEnigmaSessionKey).isEmpty()) {
			models.Enigma firstEnigma = currentStep.getEnigmas().get(0);
			if (null != firstEnigma) {
				session().put(progressEnigmaSessionKey, firstEnigma.getId());
			}
		}

		return redirect(routes.Game.go());
	}

	public static Result go() throws RepositoryException, QueryEvaluationException {
		ObjectConnection oc = Sesame.getObjectConnection();

		if (session(progressHuntSessionKey).isEmpty()) {
			return redirect(routes.Game.home());
		}
		
		models.Hunt currentHunt = oc.getObject(models.Hunt.class, models.Hunt.URI + session(progressHuntSessionKey));

		if (session(progressStepSessionKey).isEmpty()) {
			return ok(views.html.game.finish.render(currentHunt));
		}
		models.Step currentStep = oc.getObject(models.Step.class, models.Step.URI + session(progressStepSessionKey));

		if (session(progressEnigmaSessionKey).isEmpty()) {
			return ok(views.html.game.go.render(currentStep));
		}

		models.Enigma currentEnigma = oc.getObject(models.Enigma.class, models.Enigma.URI
				+ session(progressEnigmaSessionKey));

		List<models.Clue> clues = new ArrayList<models.Clue>();
		if (!session(progressClueSessionKey).isEmpty()) {
			models.Clue lastClue = oc.getObject(models.Clue.class, models.Clue.URI + session(progressClueSessionKey));

			for (models.Clue clue : currentEnigma.getClues()) {
				clues.add(clue);
				if (clue.equals(lastClue)) {
					break;
				}
			}
		}

		return ok(views.html.game.enigma.render(currentEnigma, clues));
	}
	
	private static void nextStep() throws RepositoryException, QueryEvaluationException {
		ObjectConnection oc = Sesame.getObjectConnection();
		
		models.Step step = oc.getObject(models.Step.class, models.Step.URI + session(progressStepSessionKey));
		
		models.Step nextStep = step.getNextStep();
		if (nextStep != null) {
			session().put(progressStepSessionKey, nextStep.getId());
		} else {
			session().put(progressStepSessionKey, "");
		}
	}
	
	private static void nextEnigma() throws RepositoryException, QueryEvaluationException {
		ObjectConnection oc = Sesame.getObjectConnection();

		models.Enigma enigma = oc.getObject(models.Enigma.class, models.Enigma.URI + session(progressEnigmaSessionKey));
		
		models.Enigma nextEnigma = enigma.getNextEnigma();
		if (nextEnigma != null) {
			session().put(progressEnigmaSessionKey, nextEnigma.getId());
		} else {
			session().put(progressEnigmaSessionKey, "");
			nextStep();
		}
		
		session().put(progressClueSessionKey, "");
	}

	public static Result checkAnswer() throws RepositoryException, QueryEvaluationException {
		if (session(progressEnigmaSessionKey).isEmpty()) {
			return noContent();
		}

		DynamicForm data = form().bindFromRequest();
		String answer = data.get("answer");

		ObjectConnection oc = Sesame.getObjectConnection();
		models.Enigma enigma = oc.getObject(models.Enigma.class, models.Enigma.URI + session(progressEnigmaSessionKey));

		if (enigma.getAnswer().isCorrect(answer)) {
			// Si la réponse est correcte, on peut passer à l'énigme suivante
			nextEnigma();
			return ok("true");
		} else {
			return noContent();
		}
	}

	public static Result nextClue() throws RepositoryException, QueryEvaluationException {
		System.out.println(session(progressEnigmaSessionKey));
		System.out.println(session(progressClueSessionKey));
		if (session(progressEnigmaSessionKey).isEmpty()) {
			return noContent();
		}

		ObjectConnection oc = Sesame.getObjectConnection();
		models.Enigma enigma = oc.getObject(models.Enigma.class, models.Enigma.URI + session(progressEnigmaSessionKey));

		models.Clue nextClue = null;
		if (session(progressClueSessionKey).isEmpty()) {
			if (enigma.getClues().size() > 0) {
				nextClue = enigma.getClues().get(0);
			} else {
				noContent();
			}
		} else {
			models.Clue clue = oc.getObject(models.Clue.class, models.Clue.URI + session(progressClueSessionKey))
					.getNextClue();
			if (clue == null) {
				return noContent();
			}
			nextClue = clue;
		}

		session().put(progressClueSessionKey, nextClue.getId());

		ObjectNode result = nextClue.toJson();

		return ok(result);
	}
	
	public static Result skipEnigma() throws RepositoryException, QueryEvaluationException {
		nextEnigma();
		
		return redirect(routes.Game.go());
	}
}
