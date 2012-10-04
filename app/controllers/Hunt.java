package controllers;

import java.util.UUID;

import global.Sesame;

import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.object.ObjectConnection;

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

	public static Result submitCreateForm() throws RepositoryException {
		Form<forms.Hunt> formHunt = form(forms.Hunt.class)
				.bindFromRequest();

		if (formHunt.hasErrors()) {
			return badRequest(views.html.dashboard.createHunt
					.render(formHunt));
		} else {
			forms.Hunt hunt = formHunt.get();
			models.Hunt h = new models.Hunt();
			h.setLevel(hunt.level);
			h.setPublished(false);
			
			ObjectConnection oc = Sesame.getObjectConnection();
			oc.addObject(UUID.randomUUID().toString(), h);

			return ok();
		}
	}

	public static Result edit(String hid) {
		return ok();
	}

	public static Result update(String hid) {
		return ok();
	}

	public static Result submitUpdateForm(String hid) {
		return ok();
	}

	public static Result delete(String hid) {
		return ok();
	}

	public static Result show(String hid) {
		return ok();
	}

	public static Result publish(String hid) {
		return ok();
	}

}
