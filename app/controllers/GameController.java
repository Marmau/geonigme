package controllers;

import java.util.List;

import org.codehaus.jackson.node.ObjectNode;
import org.openrdf.query.MalformedQueryException;
import org.openrdf.query.QueryEvaluationException;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.object.ObjectConnection;

import models.*;

import global.AssociatedPage;
import global.AuthenticationTokenGenerator;
import global.CurrentRequest;
import global.Page;
import global.Sesame;
import pages.EnigmaPlayPage;
import pages.HuntPlayPage;
import pages.StepPlayPage;
import play.data.DynamicForm;
import play.mvc.Result;
import play.mvc.Controller;
import repository.HuntRepository;
import repository.UserRepository;

public class GameController extends Controller {

	public static final String CURRENT_HUNT_SESSION = "current_hunt";
	public static final String CURRENT_STEP_SESSION = "current_step";
	public static final String CURRENT_ENIGMA_SESSION = "current_enigma";
	public static final String CURRENT_CLUE_SESSION = "current_clue";
	public static final String ENIGMA_SCORE_SESSION = "enigma_score";
	public static final String TOTAL_SCORE_SESSION = "total_score";

	@AssociatedPage("huntlist")
	public static Result home() throws MalformedQueryException, RepositoryException, QueryEvaluationException {
		List<Hunt> hunts = HuntRepository.getHuntsSortedByDate();

		return ok(views.html.game.home.render(hunts));
	}

	@AssociatedPage("huntplay")
	public static Result playHunt(String hid) throws RepositoryException, QueryEvaluationException {
		ObjectConnection oc = Sesame.getObjectConnection();
		Hunt hunt = oc.getObject(Hunt.class, Hunt.URI + hid);
		Hunt currentHunt = getCurrentHunt();

		if (currentHunt != null && currentHunt.equals(hunt)) {
			return redirect(routes.GameController.go());
		}

		// Initialize the game session with this hunt
		session().put(CURRENT_HUNT_SESSION, hunt.getId());
		try {
			session().put(CURRENT_STEP_SESSION, hunt.getSteps().get(0).getId());
		} catch (IndexOutOfBoundsException e) {
			session().remove(CURRENT_STEP_SESSION);
		}

		// Re-initialize the game session (except hunt)
		session().remove(CURRENT_ENIGMA_SESSION);
		session().remove(CURRENT_CLUE_SESSION);
		session().remove(ENIGMA_SCORE_SESSION);
		session().remove(TOTAL_SCORE_SESSION);

		((HuntPlayPage) CurrentRequest.page()).setMenuParameters(hunt);// Menu's
																		// parameters
		return ok(views.html.game.playHunt.render(hunt));
	}

	@AssociatedPage("stepplay")
	public static Result go() throws Exception {
		Hunt currentHunt = getCurrentHunt();
		if (currentHunt == null) {
			return redirect(routes.GameController.home());
		}

		Step currentStep = getCurrentStep();
		if (currentStep == null) {
			// Render HUNTFINISH
			HuntPlayPage page = (HuntPlayPage) Page.get("huntfinish");
			CurrentRequest.setPage(page);
			page.setMenuParameters(currentHunt);// Menu's parameters
			return ok(views.html.game.finish.render(currentHunt, getTotalScore(), getMaximumScore()));
		}
		((StepPlayPage) CurrentRequest.page()).setMenuParameters(currentStep);// Menu's
																				// parameters

		Enigma currentEnigma = getCurrentEnigma();
		if (currentEnigma == null) {
			// Render STEPPLAY
			((StepPlayPage) CurrentRequest.page()).setMenuParameters(currentStep);// Menu's
																					// parameters
			return ok(views.html.game.go.render(currentStep));
		}

		List<Clue> clues = currentEnigma.getClues();
		Clue currentClue = getCurrentClue();
		int indexOfLastClue = clues.indexOf(currentClue);
		List<Clue> usedClues = clues.subList(0, indexOfLastClue + 1);

		if (getCurrentScore() == -1) {
			// Render ENIGMAPLAY
			EnigmaPlayPage page = (EnigmaPlayPage) Page.get("enigmaplay");
			CurrentRequest.setPage(page);
			page.setMenuParameters(currentEnigma);// Menu's parameters
			return ok(views.html.game.enigma.render(currentEnigma, usedClues));

		} else if (getCurrentScore() == 0) {
			// Render ENIGMAFAIL
			EnigmaPlayPage page = (EnigmaPlayPage) Page.get("enigmafail");
			CurrentRequest.setPage(page);
			page.setMenuParameters(currentEnigma);// Menu's parameters
			return ok(views.html.game.summaryEnigmaFail.render(currentEnigma));

		} else {
			// Render ENIGMASUCCESS
			EnigmaPlayPage page = (EnigmaPlayPage) Page.get("enigmasuccess");
			CurrentRequest.setPage(page);
			page.setMenuParameters(currentEnigma);// Menu's parameters
			return ok(views.html.game.summaryEnigmaGood.render(currentEnigma, usedClues,
					clues.subList(indexOfLastClue + 1, clues.size()), getCurrentScore()));
		}
	}

	@AssociatedPage("stepplay")
	public static Result startStep() throws RepositoryException, QueryEvaluationException {
		DynamicForm data = form().bindFromRequest();
		if (!AuthenticationTokenGenerator.isValid(data.get("token"))) {
			return redirect(routes.GameController.go());
		}

		Step currentStep = getCurrentStep();

		if (null == getCurrentEnigma()) {
			Enigma firstEnigma = currentStep.getEnigmas().get(0);
			if (null != firstEnigma) {
				session().put(CURRENT_ENIGMA_SESSION, firstEnigma.getId());
			}
		}

		((StepPlayPage) CurrentRequest.page()).setMenuParameters(currentStep);// Menu's
																				// parameters
		return redirect(routes.GameController.go());
	}

	// JSON Controller
	public static Result nextClue() throws RepositoryException, QueryEvaluationException {
		if (session(CURRENT_ENIGMA_SESSION) == null) {
			return noContent();
		}
		ObjectConnection oc = Sesame.getObjectConnection();
		Enigma enigma = oc.getObject(Enigma.class, Enigma.URI + session(CURRENT_ENIGMA_SESSION));

		Clue nextClue = null;
		if (session(CURRENT_CLUE_SESSION) == null) {
			if (enigma.getClues().size() > 0) {
				nextClue = enigma.getClues().get(0);
			} else {
				noContent();
			}
		} else {
			Clue clue = oc.getObject(Clue.class, Clue.URI + session(CURRENT_CLUE_SESSION)).getNextClue();
			if (clue == null) {
				return noContent();
			}
			nextClue = clue;
		}
		session().put(CURRENT_CLUE_SESSION, nextClue.getId());

		return ok(nextClue.toJson());
	}

	public static Result finishEnigma() throws RepositoryException, QueryEvaluationException {
		DynamicForm data = form().bindFromRequest();
		if (AuthenticationTokenGenerator.isValid(data.get("token"))) {
			nextEnigma();
		}
		return redirect(routes.GameController.go());
	}

	public static Result skipEnigma() throws RepositoryException, QueryEvaluationException {
		DynamicForm data = form().bindFromRequest();
		if (!AuthenticationTokenGenerator.isValid(data.get("token"))) {
			return redirect(routes.GameController.go());
		}
		saveCurrentScore(0);
		return redirect(routes.GameController.go());
	}

	public static Result checkAnswer() throws RepositoryException, QueryEvaluationException {
		// Checked twice ?
		if (session(CURRENT_ENIGMA_SESSION) == null) {
			return noContent();
		}
		DynamicForm data = form().bindFromRequest();
		String answer = data.get("answer");

		Enigma enigma = getCurrentEnigma();

		if (null == enigma) {
			return notFound();
		}

		if (enigma.getAnswer().isCorrect(answer)) {
			int indexOfLastClue = -1;
			Clue currentClue = getCurrentClue();
			indexOfLastClue = enigma.getClues().indexOf(currentClue);
			int score = computeScore(indexOfLastClue + 1, enigma.getClues().size());

			saveCurrentScore(score);

			return ok("true");
		} else {
			return noContent();
		}
	}

	private static Hunt getCurrentHunt() throws RepositoryException, QueryEvaluationException {
		ObjectConnection oc = Sesame.getObjectConnection();

		if (null == session(CURRENT_HUNT_SESSION)) {
			return null;
		}

		return oc.getObject(Hunt.class, Hunt.URI + session(CURRENT_HUNT_SESSION));
	}

	private static Step getCurrentStep() throws RepositoryException, QueryEvaluationException {
		ObjectConnection oc = Sesame.getObjectConnection();

		if (null == session(CURRENT_STEP_SESSION)) {
			return null;
		}

		return oc.getObject(Step.class, Step.URI + session(CURRENT_STEP_SESSION));
	}

	private static Enigma getCurrentEnigma() throws RepositoryException, QueryEvaluationException {
		if (null == session(CURRENT_ENIGMA_SESSION)) {
			return null;
		}
		ObjectConnection oc = Sesame.getObjectConnection();
		return oc.getObject(Enigma.class, Enigma.URI + session(CURRENT_ENIGMA_SESSION));
	}

	private static Clue getCurrentClue() throws RepositoryException, QueryEvaluationException {
		ObjectConnection oc = Sesame.getObjectConnection();

		if (null == session(CURRENT_CLUE_SESSION)) {
			return null;
		}

		return oc.getObject(Clue.class, Clue.URI + session(CURRENT_CLUE_SESSION));
	}

	private static void nextEnigma() throws RepositoryException, QueryEvaluationException {
		Enigma enigma = getCurrentEnigma();

		if (null == enigma) {
			throw new NullPointerException();
		}

		Enigma nextEnigma = enigma.getNextEnigma();
		if (nextEnigma != null) {
			session().put(CURRENT_ENIGMA_SESSION, nextEnigma.getId());
		} else {
			session().remove(CURRENT_ENIGMA_SESSION);
			nextStep();
		}

		session().remove(CURRENT_CLUE_SESSION);
		session().remove(ENIGMA_SCORE_SESSION);
	}

	private static void nextStep() throws RepositoryException, QueryEvaluationException {
		Step step = getCurrentStep();

		if (null == step) {
			throw new NullPointerException();
		}

		Step nextStep = step.getNextStep();
		if (nextStep != null) {
			session().put(CURRENT_STEP_SESSION, nextStep.getId());
		} else {
			session().remove(CURRENT_STEP_SESSION);
		}
	}

	private static int getCurrentScore() {
		if (session(ENIGMA_SCORE_SESSION) == null) {
			return -1;
		} else {
			return Integer.parseInt(session(ENIGMA_SCORE_SESSION));
		}
	}

	private static int getTotalScore() {
		if (session(TOTAL_SCORE_SESSION) == null) {
			return 0;
		} else {
			return Integer.parseInt(session(TOTAL_SCORE_SESSION));
		}
	}

	private static int getMaximumScore() throws RepositoryException, QueryEvaluationException {
		Hunt currentHunt = getCurrentHunt();

		int max = 0;
		for (Step s : currentHunt.getSteps()) {
			for (Enigma e : s.getEnigmas()) {
				max += computeScore(0, e.getClues().size());
			}
		}

		return max;
	}

	private static void saveCurrentScore(int scoreValue) throws RepositoryException, QueryEvaluationException {
		session().put(ENIGMA_SCORE_SESSION, Integer.toString(scoreValue));
		session().put(TOTAL_SCORE_SESSION, Integer.toString(scoreValue + getTotalScore()));

		ObjectConnection oc = Sesame.getObjectConnection();

		User loggedUser = UserRepository.getLoggedUser();
		if (null != loggedUser) {
			String uri = Score.URI + loggedUser.getId() + "/" + getCurrentHunt().getId();
			Hunt currentHunt = getCurrentHunt();
			Score score = new Score();
			score.setHunt(currentHunt);
			score.setUSer(loggedUser);
			score.setValue(scoreValue);

			oc.addObject(uri, score);
		}
	}

	private static int computeScore(int usedClues, int totalClues) {
		if (totalClues == 0) {
			return 100;
		}

		int remainingClues = totalClues - usedClues;
		float rate = (float) remainingClues / (float) totalClues;

		float scoreMin = 50 / (float) totalClues + 1;
		int score = Math.round((rate * rate * (100 - scoreMin)) + scoreMin);

		return score;
	}

	public static Result clue() throws RepositoryException, QueryEvaluationException {
		DynamicForm data = form().bindFromRequest();
		if (!AuthenticationTokenGenerator.isValid(data.get("token"))) {
			return noContent();
		}

		Enigma enigma = getCurrentEnigma();
		if (null == enigma) {
			return noContent();
		}

		Clue nextClue = getCurrentClue();
		if (null == nextClue) {
			if (enigma.getClues().size() > 0) {
				nextClue = enigma.getClues().get(0);
			} else {
				noContent();
			}
		} else {
			Clue clue = nextClue.getNextClue();
			if (clue == null) {
				return noContent();
			}
			nextClue = clue;
		}

		session().put(CURRENT_CLUE_SESSION, nextClue.getId());

		ObjectNode result = nextClue.toJson();

		return ok(result);
	}

}
