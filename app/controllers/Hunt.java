package controllers;

import play.data.Form;
import play.mvc.*;

public class Hunt extends Controller {

	public static Result reset() {
		return ok();
	}

	public static Result create() {
		Form<forms.Hunt> formHunt = form(forms.Hunt.class);
		
		return ok(views.html.dashboard.createHunt.render(formHunt));
	}
	
	public static Result submitCreateForm() {
		return ok();
	}

	public static Result change(Integer hid) {
		return ok();
	}

	public static Result delete(Integer hid) {
		return ok();
	}

	public static Result publish(Integer hid) {
		return ok();
	}

}
