package controllers;

import play.data.Form;
import play.mvc.*;

public class Step extends Controller {

	public static Result create(Integer hid) {
		Form<forms.Step> formStep = form(forms.Step.class);
		
		return ok(views.html.dashboard.createStep.render(formStep));
	}
	
	public static Result submitCreateForm(Integer hid) {
		return ok();
	}
	
	public static Result change(Integer sid) {
		return ok();
	}

	public static Result delete(Integer sid) {
		return ok();
	}

	public static Result createEnigma(Integer sid) {
		return ok();
	}

}
