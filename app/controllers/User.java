package controllers;

import play.data.Form;
import play.mvc.*;

public class User extends Controller {

	public static Result submitLoginForm() {
		Form<User> userForm = form(User.class);
		
		User user = userForm.bindFromRequest().get();
		String mdp = user.getLogin();
		return ok();
	}
	
	public static Result logout() {
		return ok();
	}
	
	public static Result submitRegisterForm() {
		return ok();
	}
}
