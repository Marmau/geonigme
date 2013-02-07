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

import global.AssociatedPage;
import global.CurrentRequest;
import global.Sesame;

import models.Area;
import models.Hunt;
import models.Tag;

import org.openrdf.query.QueryLanguage;
import org.openrdf.query.QueryEvaluationException;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.object.ObjectConnection;
import org.openrdf.rio.RDFWriter;

import pages.HuntEditPage;
import pages.HuntShowPage;
import play.data.Form;
import play.mvc.*;
import repository.UserRepository;

public class HuntController extends Controller {

	@AssociatedPage("huntcreate")
	public static Result create() {
		Form<forms.Hunt> formHunt = form(forms.Hunt.class);

		return ok(views.html.dashboard.createHunt.render(formHunt));
	}

	@AssociatedPage("huntcreate")
	public static Result submitCreateForm() throws RepositoryException, DatatypeConfigurationException,
			QueryEvaluationException {
		Form<forms.Hunt> formHunt = form(forms.Hunt.class).bindFromRequest();

		if (formHunt.hasErrors()) {
			// System.out.println("has errors.");
			// System.out.println("level field: "+formHunt.get().level);
			return badRequest(views.html.dashboard.createHunt.render(formHunt));
		} else {
			Hunt hunt = formToHunt(formHunt.get());
			ObjectConnection oc = Sesame.getObjectConnection();

			Set<models.Tag> tags = formToTags(formHunt.get());
			Set<models.Tag> tagsWithURI = new HashSet<models.Tag>();
			for (models.Tag tag : tags) {
				oc.addObject(tag.urify(), tag);
				tagsWithURI.add(oc.getObject(models.Tag.class, tag.urify()));
			}
			hunt.setTags(tagsWithURI);

			String hid = UUID.randomUUID().toString();
			oc.addObject(Hunt.URI + hid, hunt);

			return redirect(routes.HuntController.show(hid));
		}
	}

	@AssociatedPage("huntedit")
	public static Result update(String hid) throws RepositoryException, QueryEvaluationException {
		ObjectConnection oc = Sesame.getObjectConnection();
		Hunt hunt = oc.getObject(Hunt.class, Hunt.URI + hid);

		if (!UserRepository.getLoggedUser().equals(hunt.getCreatedBy())) {
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

		((HuntEditPage) CurrentRequest.page()).setMenuParameters(hunt);// Menu's
																		// parameters
		return ok(views.html.dashboard.updateHunt.render(hunt, form(forms.Hunt.class).fill(formHunt)));
	}

	@AssociatedPage("huntedit")
	public static Result submitUpdateForm(String hid) throws RepositoryException, QueryEvaluationException {
		ObjectConnection oc = Sesame.getObjectConnection();
		Hunt hunt = oc.getObject(Hunt.class, Hunt.URI + hid);

		if (!UserRepository.getLoggedUser().equals(hunt.getCreatedBy())) {
			return forbidden();
		}

		Form<forms.Hunt> formHunt = form(forms.Hunt.class).bindFromRequest();

		if (formHunt.hasErrors()) {
			((HuntEditPage) CurrentRequest.page()).setMenuParameters(hunt);// Menu's
																			// parameters
			return badRequest(views.html.dashboard.createHunt.render(formHunt));
		} else {
			fillHunt(hunt, formHunt.get());

			Set<models.Tag> tags = formToTags(formHunt.get());
			Set<models.Tag> tagsWithURI = new HashSet<models.Tag>();
			for (models.Tag tag : tags) {
				oc.addObject(tag.urify(), tag);
				tagsWithURI.add(oc.getObject(models.Tag.class, tag.urify()));
			}
			hunt.setTags(tagsWithURI);

			oc.addObject(Hunt.URI + hid, hunt);

			return redirect(routes.HuntController.show(hid));
		}
	}

	@AssociatedPage("huntshow")
	public static Result show(String hid) {
		ObjectConnection oc = Sesame.getObjectConnection();

		Hunt hunt = null;
		try {
			hunt = oc.getObject(Hunt.class, Hunt.URI + hid);
		} catch (Exception e) {
			return notFound();
		}
		// If the current user didn't create it, it's a hack
		if (!UserRepository.getLoggedUser().equals(hunt.getCreatedBy())) {
			return forbidden();
		}
		((HuntShowPage) CurrentRequest.page()).setMenuParameters(hunt);// Menu's
																		// parameters
		return ok(views.html.dashboard.showHunt.render(hunt));
	}

	// @AssociatedPage("huntdelete")
	public static Result delete(String hid) {
		return ok();
	}

	// @AssociatedPage("huntpublish")
	public static Result publish(String hid) {
		return ok();
	}

	public static Result showRDF(String hid, String format) {
		ObjectConnection oc = Sesame.getObjectConnection();
		StringWriter strw = new StringWriter();
		try {
			RDFWriter writer = Sesame.getWriter(strw, format);
			String queryString = "DESCRIBE <" + Hunt.URI + hid + ">";
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
			String queryString = "DESCRIBE <" + t.urify() + ">";
			oc.prepareGraphQuery(QueryLanguage.SPARQL, queryString).evaluate(writer);
		} catch (Exception e) {
			e.printStackTrace();
			return notFound();
		}

		return ok(strw.toString());
	}

	public static Hunt formToHunt(forms.Hunt form) {
		Hunt hunt = new Hunt();
		fillHunt(hunt, form);
		return hunt;
	}

	public static void fillHunt(Hunt hunt, forms.Hunt form) {
		hunt.setDescription(form.description);
		hunt.setLabel(form.label);
		hunt.setLevel(form.level);
		hunt.setPublished(false);
		hunt.setArea(Area.createFrom(form.area));
		hunt.setCreatedBy(UserRepository.getLoggedUser());

		GregorianCalendar gcal = (GregorianCalendar) GregorianCalendar.getInstance();
		try {
			XMLGregorianCalendar now = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
			hunt.setCreatedAt(now);
			hunt.setModifiedAt(now);
		} catch (DatatypeConfigurationException e) {
		}
	}

	public static Set<models.Tag> formToTags(forms.Hunt form) {
		return Tag.createFrom(form.tags);
	}
}
