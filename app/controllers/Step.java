package controllers;

import global.Sesame;

import java.io.OutputStream;
import java.io.StringWriter;
import java.util.UUID;

import javax.xml.datatype.DatatypeConfigurationException;

import models.Position;

import org.openrdf.model.URI;
import org.openrdf.query.GraphQuery;
import org.openrdf.query.GraphQueryResult;
import org.openrdf.query.QueryEvaluationException;
import org.openrdf.query.QueryLanguage;
import org.openrdf.query.TupleQuery;
import org.openrdf.query.TupleQueryResult;
import org.openrdf.query.resultio.sparqlxml.SPARQLResultsXMLWriter;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.object.ObjectConnection;
import org.openrdf.rio.RDFHandler;
import org.openrdf.rio.rdfxml.RDFXMLWriter;
import org.openrdf.rio.turtle.TurtleWriter;


import play.data.Form;
import play.mvc.*;

public class Step extends Controller {

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

			return redirect(routes.Hunt.show(hid));
		}
	}
	
	public static Result showXML(String sid) {
		ObjectConnection oc = Sesame.getObjectConnection();
		StringWriter str = new StringWriter();
		try {
			RDFXMLWriter writer = new RDFXMLWriter(str);
			String queryString = "DESCRIBE <" + models.Step.URI + sid + ">";
			oc.prepareGraphQuery(QueryLanguage.SPARQL, queryString).evaluate(writer);
		} catch (Exception e) {
			System.out.println("Exception : " + e);
			return notFound();
		}
		return ok(str.toString());
	}
	
	public static Result showTurtle(String sid) {
		ObjectConnection oc = Sesame.getObjectConnection();
		StringWriter str = new StringWriter();
		try {
			TurtleWriter writer = new TurtleWriter(str);
			String queryString = "DESCRIBE <" + models.Step.URI + sid + ">";
			oc.prepareGraphQuery(QueryLanguage.SPARQL, queryString).evaluate(writer);
		} catch (Exception e) {
			System.out.println("Exception : " + e);
			return notFound();
		}
		return ok(str.toString());
	}

	public static Result edit(String sid) {
		return ok();
	}

	public static Result update(String sid) {
		return ok();
	}

	public static Result delete(String sid) {
		return ok();
	}

	public static Result createEnigma(String sid) {
		return ok();
	}

	public static Result submitUpdateForm(String sid) {
		return ok();
	}

	private static models.Step formToStep(forms.Step form) throws DatatypeConfigurationException {
		models.Step s = new models.Step();
		s.setDescription(form.description);
		s.setPosition(Position.createFrom(form.position, form.accuracy));

		return s;
	}
}
