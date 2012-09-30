package controllers;

import java.util.Set;

import org.openrdf.query.QueryEvaluationException;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.object.ObjectConnection;

import forms.*;
import global.Sesame;
import models.Hunt;
import play.data.Form;
import play.mvc.*;

import views.html.*;

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

		ObjectConnection oc = Sesame.getObjectConnection();
		oc.clear();
		oc.addObject(h);

		return ok();
	}

	public static Result test2() throws RepositoryException,
			QueryEvaluationException {
		ObjectConnection oc = Sesame.getObjectConnection();

		Set<gngm.Hunt> result = oc.getObjects(gngm.Hunt.class).asSet();

		for (gngm.Hunt h : result) {
			System.out.println(h.getLevel());
			System.out.println(h.getPublished());
		}

		return ok();
	}

}