package controllers;

import java.util.List;

import org.openrdf.query.QueryEvaluationException;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.object.ObjectConnection;

import forms.Login;
import forms.Register;
import global.Sesame;

import play.data.Form;
import play.mvc.*;

public class Manager extends Controller {

	public static Result dashboard() throws RepositoryException, QueryEvaluationException {
		ObjectConnection oc = Sesame.getObjectConnection();
//		String uid = session("user");
//
//		models.User user = oc.getObject(models.User.class, models.User.URI + uid);
//		List<models.Hunt> hunts = user.getHunts();
		
		List<models.Hunt> hunts = oc.getObjects(models.Hunt.class).asList(); // provisoirement

		return ok(views.html.dashboard.dashboard.render(hunts));

	}

	public static Result login() {
		String user = session("user");
		if (user != null)
			return ok();// views.html.dashboard.mainDashboard.render());

		Form<Login> formLogin = form(Login.class);
		Form<Register> formRegister = form(Register.class);

		return ok(views.html.global.login.render(formLogin, formRegister));
	}

	public static Result register() {
		String user = session("user");
		if (user != null)
			return ok();// views.html.dashboard.mainDashboard.render());

		Form<Login> formLogin = form(Login.class);
		Form<Register> formRegister = form(Register.class);

		return ok(views.html.global.login.render(formLogin, formRegister));
	}
}
