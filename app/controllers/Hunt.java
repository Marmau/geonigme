package controllers;

import java.util.GregorianCalendar;
import java.util.UUID;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import global.Sesame;

import models.Area;
import models.Tag;

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

	public static Result submitCreateForm() throws RepositoryException, DatatypeConfigurationException {
		Form<forms.Hunt> formHunt = form(forms.Hunt.class).bindFromRequest();

		if (formHunt.hasErrors()) {
			return badRequest(views.html.dashboard.createHunt.render(formHunt));
		} else {
			models.Hunt h = formToHunt(formHunt.get());
			ObjectConnection oc = Sesame.getObjectConnection();

			String hid = UUID.randomUUID().toString();
			oc.addObject(models.Hunt.URI + hid, h);

			return redirect(routes.Hunt.show(hid));
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
		ObjectConnection oc = Sesame.getObjectConnection();

		models.Hunt h = null;
		try {
			h = oc.getObject(models.Hunt.class, models.Hunt.URI + hid);
		} catch (Exception e) {
			return notFound();
		}

		return ok(views.html.dashboard.showHunt.render(h));
	}

	public static Result publish(String hid) {
		return ok();
	}

	private static models.Hunt formToHunt(forms.Hunt form) throws DatatypeConfigurationException {
		models.Hunt h = new models.Hunt();
		h.setDescription(form.description);
		h.setLabel(form.label);
		h.setLevel(form.level);
		h.setPublished(false);
		for (Tag t : Tag.createFrom(form.tags)) {
			System.out.println(t.getName());
		}
		h.setTags(Tag.createFrom(form.tags));
		h.setArea(Area.createFrom(form.area));

		GregorianCalendar gcal = (GregorianCalendar) GregorianCalendar.getInstance();
		XMLGregorianCalendar xgcal = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
		h.setCreatedAt(xgcal);

		return h;
	}

}
