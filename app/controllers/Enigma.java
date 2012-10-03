package controllers;

import play.data.Form;
import play.mvc.*;

public class Enigma extends Controller {

	public static Result create(Integer sid) {
		Form<forms.Enigma> formEnigma = form(forms.Enigma.class);
		System.out.println(request());
		return ok(views.html.dashboard.createEnigma.render(formEnigma));
	}
	
	public static Result submitCreateForm(Integer sid) {
		Form<forms.Enigma> formEnigma = form(forms.Enigma.class).bindFromRequest();
        
        if(formEnigma.hasErrors()) {
            return badRequest(views.html.dashboard.createEnigma.render(formEnigma));
        } else {
            forms.Enigma enigma = formEnigma.get();
            System.out.println(enigma.description);

            return ok();
        }
	}
	
	public static Result change(Integer eid) {
		return ok();
	}

	public static Result delete(Integer eid) {
		return ok();
	}

}
