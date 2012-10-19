package controllers;


import org.openrdf.repository.RepositoryException;

import forms.*;
import global.Sesame;
import play.data.Form;
import play.mvc.*;

public class Application extends Controller {

	public static Result index() {
		return ok(views.html.global.index.render());
	}

	public static Result submitContactForm() {
		// TODO
		return TODO;
	}

	public static Result login() {
		Form<Login> formLogin = form(Login.class);
		Form<Register> formRegister = form(Register.class);

		return ok(views.html.global.login.render(formLogin, formRegister));
	}
	
	public static Result reset() throws RepositoryException {
		Sesame.getObjectConnection().clear();
		
		return ok();
	}
}