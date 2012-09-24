package controllers;

import java.util.Set;

import org.openrdf.query.QueryEvaluationException;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.object.ObjectConnection;

import global.Sesame;
import models.Hunt;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {

	public static Result index() {
		return ok(index.render("Your new application is ready."));
	}

	public static Result test1() throws RepositoryException {
		Hunt h = new Hunt();
		h.setLevel(3);

		ObjectConnection oc = Sesame.getObjectConnection();
		oc.addObject(h);

		return ok();
	}

	public static Result test2() throws RepositoryException,
			QueryEvaluationException {
		ObjectConnection oc = Sesame.getObjectConnection();

		Set<gngm.Hunt> result = oc.getObjects(gngm.Hunt.class).asSet();

		for (gngm.Hunt h : result) {
			System.out.println(h.getLevel());
		}

		return ok();
	}

}