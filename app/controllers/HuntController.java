package controllers;

import java.io.StringWriter;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import global.Sesame;

import models.Area;
import models.Tag;

import org.openrdf.query.QueryLanguage;
import org.openrdf.query.QueryEvaluationException;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.object.ObjectConnection;
import org.openrdf.rio.RDFWriter;

import play.data.Form;
import play.mvc.*;

public class HuntController extends Controller {

	public static Result create() {
		if (!UserController.isLogged()) {
			return redirect(routes.ApplicationController.index());
		}
		
		Form<forms.Hunt> formHunt = form(forms.Hunt.class);

		return ok(views.html.dashboard.createHunt.render(formHunt));
	}

	public static Result submitCreateForm() throws RepositoryException, DatatypeConfigurationException, QueryEvaluationException {
		if (!UserController.isLogged()) {
			return redirect(routes.ApplicationController.index());
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
				oc.addObject(models.Tag.URI + tag.urify(), tag);
				tagsWithURI.add(oc.getObject(models.Tag.class, models.Tag.URI + tag.urify()));
			}
			hunt.setTags(tagsWithURI);
			
			String hid = UUID.randomUUID().toString();
			oc.addObject(models.Hunt.URI + hid, hunt);

			return redirect(routes.HuntController.show(hid));
		}
	}

	public static Result update(String hid) throws RepositoryException,	QueryEvaluationException {
		if (!UserController.isLogged()) {
			return redirect(routes.ApplicationController.index());
		}
		
		ObjectConnection oc = Sesame.getObjectConnection();
		models.Hunt hunt = oc.getObject(models.Hunt.class, models.Hunt.URI + hid);
		
		if (!UserController.getLoggedUser().equals(hunt.getCreatedBy())) {
			return forbidden();
		}
		
		forms.Hunt formHunt = new forms.Hunt();
		formHunt.label = hunt.getLabel();
		formHunt.description = hunt.getDescription();
		formHunt.level = hunt.getLevel();
		formHunt.area = hunt.getArea().toTemplateString();
		
		Iterator<models.Tag> it = hunt.getTags().iterator();
		if (it.hasNext()) {
			models.Tag firstTag = it.next();
			formHunt.tags = firstTag.getName();
			
			while (it.hasNext()) {
				formHunt.tags += ", " + it.next().getName();
			}
		}

		return ok(views.html.dashboard.updateHunt.render(hunt, form(forms.Hunt.class).fill(formHunt)));
	}

	public static Result submitUpdateForm(String hid) throws RepositoryException, QueryEvaluationException {
		if (!UserController.isLogged()) {
			return redirect(routes.ApplicationController.index());
		}
		
		ObjectConnection oc = Sesame.getObjectConnection();
		models.Hunt hunt = oc.getObject(models.Hunt.class, models.Hunt.URI + hid);

		if (!UserController.getLoggedUser().equals(hunt.getCreatedBy())) {
			return forbidden();
		}

		Form<forms.Hunt> formHunt = form(forms.Hunt.class).bindFromRequest();

		if (formHunt.hasErrors()) {
			return badRequest(views.html.dashboard.createHunt.render(formHunt));
		} else {
			fillHunt(hunt, formHunt.get());
			
			Set<models.Tag> tags = formToTags(formHunt.get());
			Set<models.Tag> tagsWithURI = new HashSet<models.Tag>();
			for (models.Tag tag: tags) {
				oc.addObject(models.Tag.URI + tag.urify(), tag);
				tagsWithURI.add(oc.getObject(models.Tag.class, models.Tag.URI + tag.urify()));
			}
			hunt.setTags(tagsWithURI);
			
			oc.addObject(models.Hunt.URI + hid, hunt);

			return redirect(routes.HuntController.show(hid));
		}
	}

	public static Result delete(String hid) {
		if (!UserController.isLogged()) {
			return redirect(routes.ApplicationController.index());
		}
		
		return ok();
	}

	public static Result show(String hid) {
		if (!UserController.isLogged()) {
			return redirect(routes.ApplicationController.index());
		}
		
		ObjectConnection oc = Sesame.getObjectConnection();
		
		models.Hunt h = null;
		try {
			h = oc.getObject(models.Hunt.class, models.Hunt.URI + hid);
		} catch (Exception e) {
			return notFound();
		}
		

		if (!UserController.getLoggedUser().equals(h.getCreatedBy())) {
			return forbidden();
		}

		return ok(views.html.dashboard.showHunt.render(h));
	}
	
	public static Result showRDF(String hid, String format) {
		ObjectConnection oc = Sesame.getObjectConnection();
		StringWriter strw = new StringWriter();
		try {
			RDFWriter writer = Sesame.getWriter(strw, format);
			String queryString = "DESCRIBE <" + models.Hunt.URI + hid + ">";
			oc.prepareGraphQuery(QueryLanguage.SPARQL, queryString).evaluate(writer);
		} catch (Exception e) {
			e.printStackTrace();
			return notFound();
		}
		
		return ok(strw.toString());
	}
	
	public static Result showTagRDF(String name, String format) {
		ObjectConnection oc = Sesame.getObjectConnection();
		StringWriter strw = new StringWriter();
		try {
			RDFWriter writer = Sesame.getWriter(strw, format);
			Tag t = new Tag();
			t.setName(name);
			String queryString = "DESCRIBE <" + models.Tag.URI + t.urify() + ">";
			oc.prepareGraphQuery(QueryLanguage.SPARQL, queryString).evaluate(writer);
		} catch (Exception e) {
			e.printStackTrace();
			return notFound();
		}
		
		return ok(strw.toString());
	}

	public static Result publish(String hid) {
		if (!UserController.isLogged()) {
			return redirect(routes.ApplicationController.index());
		}
		
		return ok();
	}

	private static models.Hunt formToHunt(forms.Hunt form) {
		models.Hunt h = new models.Hunt();
		fillHunt(h, form);

		return h;
	}
	
	private static void fillHunt(models.Hunt hunt, forms.Hunt form) {
		hunt.setDescription(form.description);
		hunt.setLabel(form.label);
		hunt.setLevel(form.level);
		hunt.setPublished(false);
		hunt.setArea(Area.createFrom(form.area));
		hunt.setCreatedBy(UserController.getLoggedUser());
		
		GregorianCalendar gcal = (GregorianCalendar) GregorianCalendar.getInstance();

		try {
			XMLGregorianCalendar now = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
			hunt.setCreatedAt(now);
			hunt.setModifiedAt(now);
		} catch (DatatypeConfigurationException e) {
		}

	}
	
	private static Set<models.Tag> formToTags(forms.Hunt form) {
		return Tag.createFrom(form.tags);
	}
}
