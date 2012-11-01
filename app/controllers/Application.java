package controllers;

import org.openrdf.query.QueryEvaluationException;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.object.ObjectConnection;

import global.Sesame;
import play.mvc.*;

public class Application extends Controller {

	public static Result index() {
		return ok(views.html.global.index.render());
	}

	public static Result submitContactForm() {
		// TODO
		return TODO;
	}

	public static Result reset() throws RepositoryException, QueryEvaluationException {
		ObjectConnection oc = Sesame.getObjectConnection();
		 oc.clear();
		for (models.Enigma e : oc.getObjects(models.Enigma.class).asSet()) {
			oc.removeDesignation(e, models.Enigma.class);
		}

		return ok();
	}
}