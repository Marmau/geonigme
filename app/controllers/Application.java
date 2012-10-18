package controllers;

import java.util.HashSet;

import org.openrdf.query.QueryEvaluationException;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.object.ObjectConnection;

import forms.*;
import global.Sesame;
import models.Hunt;
import models.Step;
import play.data.Form;
import play.mvc.*;

public class Application extends Controller {

	public static Result index() {
		return ok(views.html.global.index.render());
	}

	public static Result submitContactForm() {
		// TODO
		return ok();
	}

	public static Result login() {
		Form<Login> formLogin = form(Login.class);
		Form<Register> formRegister = form(Register.class);

		return ok(views.html.global.login.render(formLogin, formRegister));
	}

	public static Result test1() throws RepositoryException {
		Hunt h = new Hunt();
		h.setLevel(3);
		h.setPublished(false);
		Step s = new Step();
		// s.setLat(3.443f);
		// h.addStep(s);

		ObjectConnection oc = Sesame.getObjectConnection();
		oc.clear();
		oc.addObject(h);

		Hunt h2 = new Hunt();
		h2.setLevel(1);
		oc.addObject("http://example.com/1", h2);

		return ok();
	}

	public static Result test2() throws RepositoryException, QueryEvaluationException {
		ObjectConnection oc = Sesame.getObjectConnection();

		Hunt h = oc.getObject(Hunt.class, "http://example.com/1");
		System.out.println(h.getLabel());
		System.out.println(h.getLevel());
		System.out.println(h.isPublished());
		System.out.println(h.getCreatedAt());
		for (Step s : h.getSteps()) {
			// System.out.println(s.getLat());
		}

		HashSet<org.openrdf.result.Result<Hunt>> h3 = (HashSet<org.openrdf.result.Result<Hunt>>) h.getHuntsWithLevel(3);
		h = (Hunt) h3.iterator().next();
		System.out.println(h.getLabel());
		System.out.println(h.getLevel());
		System.out.println(h.isPublished());
		System.out.println(h.getCreatedAt());
		for (Step s : h.getSteps()) {
			// System.out.println(s.getLat());
		}

		return ok();
	}
}