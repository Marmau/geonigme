package controllers;

import play.data.Form;
import play.mvc.*;

public class Step extends Controller {

	public static Result create(String hid) {
		Form<forms.Step> formStep = form(forms.Step.class);
		
		return ok(views.html.dashboard.createStep.render(formStep));
	}
	
	public static Result submitCreateForm(String hid) {
		return ok();
	}
	
	public static Result change(String sid) {
		return ok();
	}

	public static Result delete(String sid) {
		return ok();
	}

	public static Result createEnigma(String sid) {
		return ok();
	}

}
