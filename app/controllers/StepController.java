package controllers;

import global.AssociatedPage;
import global.CurrentRequest;
import global.Sesame;

import java.io.StringWriter;
import java.util.UUID;

import javax.xml.datatype.DatatypeConfigurationException;

import models.Hunt;
import models.Step;

import org.openrdf.query.QueryEvaluationException;
import org.openrdf.query.QueryLanguage;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.object.ObjectConnection;
import org.openrdf.rio.RDFWriter;

import pages.StepCreatePage;
import pages.StepEditPage;
import play.data.Form;
import play.mvc.*;

public class StepController extends Controller {

	@AssociatedPage("stepcreate")
	public static Result create(String hid) throws RepositoryException, QueryEvaluationException {
		Form<forms.Step> formStep = form(forms.Step.class);
		ObjectConnection oc = Sesame.getObjectConnection();

		Hunt hunt = oc.getObject(Hunt.class, Hunt.URI + hid);

		((StepCreatePage) CurrentRequest.page()).setMenuParameters(hunt);// Menu's
																			// parameters
		return ok(views.html.dashboard.createStep.render(hunt, formStep));
	}

	@AssociatedPage("stepcreate")
	public static Result submitCreateForm(String hid) throws DatatypeConfigurationException, RepositoryException,
			QueryEvaluationException {
		Form<forms.Step> formStep = form(forms.Step.class).bindFromRequest();
		ObjectConnection oc = Sesame.getObjectConnection();
		Hunt hunt = oc.getObject(Hunt.class, Hunt.URI + hid);

		if (formStep.hasErrors()) {
			((StepCreatePage) CurrentRequest.page()).setMenuParameters(hunt);// Menu's
																				// parameters
			return badRequest(views.html.dashboard.createStep.render(hunt, formStep));
		} else {
			Step step = formToStep(formStep.get());
			step.setHunt(hunt);
			step.setNumber(hunt.getSteps().size() + 1);

			String sid = UUID.randomUUID().toString();
			oc.addObject(Step.URI + sid, step);

			return redirect(routes.HuntController.show(hid));
		}
	}

	public static Result showRDF(String sid, String format) {
		ObjectConnection oc = Sesame.getObjectConnection();
		StringWriter strw = new StringWriter();
		try {
			RDFWriter writer = Sesame.getWriter(strw, format);
			String queryString = "DESCRIBE <" + Step.URI + sid + ">";
			oc.prepareGraphQuery(QueryLanguage.SPARQL, queryString).evaluate(writer);
		} catch (Exception e) {
			e.printStackTrace();
			return notFound();
		}

		return ok(strw.toString());
	}

	@AssociatedPage("stepedit")
	public static Result update(String sid) throws RepositoryException, QueryEvaluationException {
		ObjectConnection oc = Sesame.getObjectConnection();
		Step step = oc.getObject(Step.class, Step.URI + sid);

		forms.Step formStep = new forms.Step();
		formStep.description = step.getDescription();
		formStep.accuracy = step.getPosition().getAccuracy();
		formStep.position = step.getPosition().toTemplateString();

		((StepEditPage) CurrentRequest.page()).setMenuParameters(step);// Menu's parameters
		
		return ok(views.html.dashboard.updateStep.render(step, form(forms.Step.class).fill(formStep)));
	}

	@AssociatedPage("stepedit")
	public static Result submitUpdateForm(String sid) throws RepositoryException, QueryEvaluationException {
		Form<forms.Step> formStep = form(forms.Step.class).bindFromRequest();
		ObjectConnection oc = Sesame.getObjectConnection();
		Step step = oc.getObject(Step.class, Step.URI + sid);

		if (formStep.hasErrors()) {
			((StepEditPage) CurrentRequest.page()).setMenuParameters(step);// Menu's
																			// parameters
			return badRequest(views.html.dashboard.updateStep.render(step, formStep));
		} else {
			fillStep(step, formStep.get());

			oc.addObject(Step.URI + sid, step);

			return redirect(routes.HuntController.show(step.getHunt().getId()));
		}
	}

	public static Result delete(String sid) {
		return ok();
	}

	private static Step formToStep(forms.Step form) {
		Step step = new Step();
		fillStep(step, form);
		return step;
	}

	private static void fillStep(Step step, forms.Step form) {
		step.setDescription(form.description);
		step.setPosition(models.Position.createFrom(form.position, form.accuracy));
	}
}
