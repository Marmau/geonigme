package controllers;

import play.data.Form;
import play.mvc.*;

public class Enigma extends Controller {

	public static Result create(Integer sid) {
		Form<forms.Enigma> formHunt = form(forms.Enigma.class);
		
		return ok(views.html.dashboard.createEnigma.render(formHunt));
	}
	
	public static Result submitCreateForm(Integer sid) {
		return ok();
	}
	
	public static Result change(Integer eid) {
		return ok();
	}

	public static Result delete(Integer eid) {
		return ok();
	}

}
