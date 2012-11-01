package controllers;

import java.util.List;

import models.NS;

import org.openrdf.query.MalformedQueryException;
import org.openrdf.query.QueryEvaluationException;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.object.ObjectConnection;
import org.openrdf.repository.object.ObjectQuery;

import global.Sesame;
import play.mvc.Result;
import play.mvc.Controller;

public class Game extends Controller {
	
	public static final String progressHuntSessionKey = "progressHunt";
	public static final String progressStepSessionKey = "progressStep";
	public static final String progressEnigmaSessionKey = "progressEnigma";

	public static Result home() throws MalformedQueryException, RepositoryException, QueryEvaluationException {
		ObjectConnection oc = Sesame.getObjectConnection();
		ObjectQuery query = oc.prepareObjectQuery(NS.PREFIX + 
				"SELECT ?hunt WHERE { ?hunt gngm:modifiedAt ?date } ORDER BY DESC(?date) LIMIT 20");
		
		List<models.Hunt> hunts = query.evaluate(models.Hunt.class).asList();
		
		return ok(views.html.game.home.render(hunts));
	}
	
	public static Result playHunt(String hid) throws RepositoryException, QueryEvaluationException {
		session().put(progressHuntSessionKey, "");
		session().put(progressStepSessionKey, "");
		session().put(progressEnigmaSessionKey, "");
		
		ObjectConnection oc = Sesame.getObjectConnection();
		models.Hunt hunt = oc.getObject(models.Hunt.class, models.Hunt.URI + hid);
		
		return ok(views.html.game.playHunt.render(hunt));
	}
	
	public static Result goNext(String hid) throws RepositoryException, QueryEvaluationException {
		ObjectConnection oc = Sesame.getObjectConnection();
		
		models.Step currentStep = oc.getObject(models.Step.class, models.Step.URI + session(progressStepSessionKey));
		
		models.Step nextStep = currentStep.getNextStep();
		if (nextStep != null) {				
			session().put(progressStepSessionKey, nextStep.getId());
		} else {
			session().put(progressStepSessionKey, "");
		}
		
		return redirect(routes.Game.go(hid));
	}
	
	public static Result go(String hid) throws RepositoryException, QueryEvaluationException {
		ObjectConnection oc = Sesame.getObjectConnection();
		
		models.Hunt currentHunt = null; 
		if (!session(progressHuntSessionKey).isEmpty()) {
			currentHunt = oc.getObject(models.Hunt.class, models.Hunt.URI + session(progressHuntSessionKey));
		}
		
		models.Hunt hunt = oc.getObject(models.Hunt.class, models.Hunt.URI + hid);
		if (!hunt.equals(currentHunt)) {
			session().put(progressHuntSessionKey, hunt.getId());
			try {				
				session().put(progressStepSessionKey, hunt.getSteps().get(0).getId());
			} catch (IndexOutOfBoundsException e) {
				session().put(progressStepSessionKey, "");
			}
			
			session().put(progressEnigmaSessionKey, "");
		}
		
		if (session(progressStepSessionKey).isEmpty()) {
			return ok(views.html.game.finish.render(hunt));
		}
		models.Step currentStep = oc.getObject(models.Step.class, models.Step.URI + session(progressStepSessionKey));
		
		if (session(progressEnigmaSessionKey).isEmpty()) {			
			return ok(views.html.game.go.render(currentStep));
		}

		models.Enigma currentEnigma = oc.getObject(models.Enigma.class, models.Enigma.URI + session(progressEnigmaSessionKey));
		
		models.Enigma nextEnigma = currentEnigma.getNextEnigma();
		if (nextEnigma != null) {
			session().put(progressEnigmaSessionKey, nextEnigma.getId());			
		} else {
			session().put(progressEnigmaSessionKey, "");
		}
		
		return ok(views.html.game.enigma.render(currentEnigma));	
	}
}
