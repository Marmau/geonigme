package controllers;

import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import global.Sesame;

import models.old.Tag;

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

	public static Result submitCreateForm() throws RepositoryException,
			DatatypeConfigurationException {
		Form<forms.Hunt> formHunt = form(forms.Hunt.class).bindFromRequest();

		System.out.println("AJOUTE");

		if (formHunt.hasErrors()) {
			System.out.println(formHunt.errors());

			return badRequest(views.html.dashboard.createHunt.render(formHunt));
		} else {
			System.out.println("AJOUTE");

			forms.Hunt hunt = formHunt.get();
			models.Hunt h = new models.Hunt();
			h.setLabel(hunt.label);
			h.setLevel(hunt.level);
			h.setPublished(false);
			Set<Tag> tags = new HashSet<Tag>();
			for (String nameTag : hunt.tags.split(",")) {
				Tag tag = new Tag();
				tag.setName(nameTag.trim());
				tags.add(tag);
			}
//			h.setTags(tags);

			GregorianCalendar gcal = (GregorianCalendar) GregorianCalendar.getInstance();
			XMLGregorianCalendar xgcal = DatatypeFactory.newInstance()
					.newXMLGregorianCalendar(gcal);
			h.setCreatedAt(xgcal);

			ObjectConnection oc = Sesame.getObjectConnection();
			oc.addObject(models.Hunt.NS + UUID.randomUUID().toString(), h);

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
