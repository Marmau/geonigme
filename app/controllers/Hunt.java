package controllers;

import java.io.UnsupportedEncodingException;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import global.Sesame;

import models.Area;
import models.Tag;

import org.openrdf.query.QueryEvaluationException;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.object.ObjectConnection;

import play.data.Form;
import play.mvc.*;
import play.templates.Hash;

public class Hunt extends Controller {

	public static Result reset() {
		return ok();
	}

	public static Result create() {
		if (!User.isLogged()) {
			return redirect(routes.Application.index());
		}
		
		Form<forms.Hunt> formHunt = form(forms.Hunt.class);

		return ok(views.html.dashboard.createHunt.render(formHunt));
	}

	public static Result submitCreateForm() throws RepositoryException, DatatypeConfigurationException, UnsupportedEncodingException, QueryEvaluationException {
		if (!User.isLogged()) {
			return redirect(routes.Application.index());
		}
		
		Form<forms.Hunt> formHunt = form(forms.Hunt.class).bindFromRequest();

		if (formHunt.hasErrors()) {
			return badRequest(views.html.dashboard.createHunt.render(formHunt));
		} else {
			models.Hunt hunt = formToHunt(formHunt.get());
			ObjectConnection oc = Sesame.getObjectConnection();
			
			Set<models.Tag> tags = formToTags(formHunt.get());
			Set<models.Tag> tagsWithURI = new HashSet<models.Tag>();
			for (models.Tag tag: tags) {
				System.out.println(models.Tag.URI + tag.urify());
				oc.addObject(models.Tag.URI + tag.urify(), tag);
				tagsWithURI.add(oc.getObject(models.Tag.class, models.Tag.URI + tag.urify()));
			}
			hunt.setTags(tagsWithURI);
			
			String hid = UUID.randomUUID().toString();
			oc.addObject(models.Hunt.URI + hid, hunt);

			return redirect(routes.Hunt.show(hid));
		}
	}

	public static Result update(String hid) {
		if (!User.isLogged()) {
			return redirect(routes.Application.index());
		}
		
		return ok();
	}

	public static Result submitUpdateForm(String hid) {
		if (!User.isLogged()) {
			return redirect(routes.Application.index());
		}
		
		return ok();
	}

	public static Result delete(String hid) {
		if (!User.isLogged()) {
			return redirect(routes.Application.index());
		}
		
		return ok();
	}

	public static Result show(String hid) {
		if (!User.isLogged()) {
			return redirect(routes.Application.index());
		}
		
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
		if (!User.isLogged()) {
			return redirect(routes.Application.index());
		}
		
		return ok();
	}

	private static models.Hunt formToHunt(forms.Hunt form) throws DatatypeConfigurationException {
		models.Hunt h = new models.Hunt();
		h.setDescription(form.description);
		h.setLabel(form.label);
		h.setLevel(form.level);
		h.setPublished(false);
		h.setArea(Area.createFrom(form.area));
		h.setCreatedBy(User.getLoggedUser());
		
		System.out.println(User.getLoggedUser());

		GregorianCalendar gcal = (GregorianCalendar) GregorianCalendar.getInstance();
		XMLGregorianCalendar now = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
		h.setCreatedAt(now);
		h.setModifiedAt(now);

		return h;
	}
	
	private static Set<models.Tag> formToTags(forms.Hunt form) {
		return Tag.createFrom(form.tags);
	}

}
