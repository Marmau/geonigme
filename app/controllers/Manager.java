package controllers;

import java.util.ArrayList;

import forms.Login;
import forms.Register;

import play.data.Form;
import play.mvc.*;
import models.Hunt;
import repository.HuntRepository;

public class Manager extends Controller {

	public static Result dashboard() {
		String user = session("user");
		if (user == null)
			return ok(views.html.global.index.render());
		
		HuntRepository huntRepo = new HuntRepository();
		ArrayList<Hunt> hunts = huntRepo.getHuntsByAuthor(Integer.parseInt(user));
		
		return ok();//views.html.dashboard.mainDashboard.render("hunts"));
		
	}

	public static Result login() {
		String user = session("user");
		if (user != null)
			return ok();//views.html.dashboard.mainDashboard.render());
		
		Form<Login> formLogin = form(Login.class);
		Form<Register> formRegister = form(Register.class);
				
		return ok(views.html.global.login.render(formLogin, formRegister));
	}

	public static Result register() {
		String user = session("user");
		if (user != null)
			return ok();//views.html.dashboard.mainDashboard.render());
		
		Form<Login> formLogin = form(Login.class);
		Form<Register> formRegister = form(Register.class);
				
		return ok(views.html.global.login.render(formLogin, formRegister));
	}
}
