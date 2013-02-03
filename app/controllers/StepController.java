package controllers;

import global.Sesame;

import java.io.StringWriter;
import java.util.UUID;

import javax.xml.datatype.DatatypeConfigurationException;

import org.openrdf.query.QueryEvaluationException;
import org.openrdf.query.QueryLanguage;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.object.ObjectConnection;
import org.openrdf.rio.RDFWriter;

import play.data.Form;
import play.mvc.*;

public class StepController extends Controller {

	public static Result create(String hid) throws RepositoryException, QueryEvaluationException {
		Form<forms.Step> formStep = form(forms.Step.class);
		ObjectConnection oc = Sesame.getObjectConnection();

		models.Hunt hunt = oc.getObject(models.Hunt.class, models.Hunt.URI + hid);

		return ok(views.html.dashboard.createStep.render(hunt, formStep));
	}

	public static Result submitCreateForm(String hid) throws DatatypeConfigurationException, RepositoryException, QueryEvaluationException {
		Form<forms.Step> formStep = form(forms.Step.class).bindFromRequest();
		ObjectConnection oc = Sesame.getObjectConnection();
		models.Hunt hunt = oc.getObject(models.Hunt.class, models.Hunt.URI + hid);

		if (formStep.hasErrors()) {
			return badRequest(views.html.dashboard.createStep.render(hunt, formStep));
		} else {
			models.Step step = formToStep(formStep.get());
			step.setHunt(hunt);
			step.setNumber(hunt.getSteps().size() + 1);

			String sid = UUID.randomUUID().toString();
			oc.addObject(models.Step.URI + sid, step);

			return redirect(routes.HuntController.show(hid));
		}
	}
	
	public static Result showRDF(String sid, String format) {
		ObjectConnection oc = Sesame.getObjectConnection();
		StringWriter strw = new StringWriter();
		try {
			RDFWriter writer = Sesame.getWriter(strw, format);
			String queryString = "DESCRIBE <" + models.Step.URI + sid + ">";
			oc.prepareGraphQuery(QueryLanguage.SPARQL, queryString).evaluate(writer);
		} catch (Exception e) {
			e.printStackTrace();
			return notFound();
		}
		
		return ok(strw.toString());
	}

	public static Result update(String sid) throws RepositoryException,	QueryEvaluationException {
		ObjectConnection oc = Sesame.getObjectConnection();

		models.Step step = oc.getObject(models.Step.class, models.Step.URI + sid);
		
		forms.Step formStep = new forms.Step();
		formStep.description = step.getDescription();
		formStep.accuracy = step.getPosition().getAccuracy();
		formStep.position = step.getPosition().toTemplateString();

		return ok(views.html.dashboard.updateStep.render(step, form(forms.Step.class).fill(formStep)));
	}
	
	public static Result submitUpdateForm(String sid) throws RepositoryException, QueryEvaluationException {
		Form<forms.Step> formStep = form(forms.Step.class).bindFromRequest();
		ObjectConnection oc = Sesame.getObjectConnection();
		models.Step step = oc.getObject(models.Step.class, models.Step.URI + sid);

		if (formStep.hasErrors()) {
			return badRequest(views.html.dashboard.updateStep.render(step, formStep));
		} else {
			fillStep(step, formStep.get());

			oc.addObject(models.Step.URI + sid, step);

			return redirect(routes.HuntController.show(step.getHunt().getId()));
		}
	}

	public static Result delete(String sid) {
		return ok();
	}

	private static models.Step formToStep(forms.Step form) {
		models.Step s = new models.Step();
		fillStep(s, form);

		return s;
	}
	
	private static void fillStep(models.Step step, forms.Step form) {
		step.setDescription(form.description);
		step.setPosition(models.Position.createFrom(form.position, form.accuracy));
	}
}