package controllers;

import java.io.StringWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import global.Sesame;

import models.Answer;
import models.Clue;

import org.openrdf.query.QueryEvaluationException;
import org.openrdf.query.QueryLanguage;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.object.ObjectConnection;
import org.openrdf.rio.RDFHandler;
import org.openrdf.rio.rdfxml.RDFXMLWriter;
import org.openrdf.rio.turtle.TurtleWriter;

import play.data.Form;
import play.mvc.*;

public class Enigma extends Controller {

	public static Result create(String sid) throws RepositoryException, QueryEvaluationException {
		Form<forms.Enigma> formEnigma = form(forms.Enigma.class);
		ObjectConnection oc = Sesame.getObjectConnection();

		models.Step step = oc.getObject(models.Step.class, models.Step.URI + sid);
		
		formEnigma.fill(new forms.Enigma());

		return ok(views.html.dashboard.createEnigma.render(step, formEnigma));
	}

	public static Result submitCreateForm(String sid) throws RepositoryException, QueryEvaluationException {
		Form<forms.Enigma> formEnigma = form(forms.Enigma.class).bindFromRequest();
		ObjectConnection oc = Sesame.getObjectConnection();
		models.Step step = oc.getObject(models.Step.class, models.Step.URI + sid);

		if (formEnigma.hasErrors()) {
			return badRequest(views.html.dashboard.createEnigma.render(step, formEnigma));
		} else {
			models.Enigma enigma = formToEnigma(formEnigma.get());
			enigma.setStep(step);
			enigma.setNumber(step.getEnigmas().size() + 1);
			
			String eid = UUID.randomUUID().toString();
			oc.addObject(models.Enigma.URI + eid, enigma);
			
			enigma = oc.getObject(models.Enigma.class, models.Enigma.URI + eid);
			
			for (models.Clue clue: formToClues(formEnigma.get())) {
				clue.setEnigma(enigma);
				String cid = UUID.randomUUID().toString();
				oc.addObject(models.Clue.URI + cid, clue);
			}
			
			models.Answer answer = formToAnswer(formEnigma.get());
			answer.setEnigma(enigma);
			String aid = UUID.randomUUID().toString();
			oc.addObject(models.Answer.URI + aid, answer);
			
			oc.commit();

			return redirect(routes.Hunt.show(step.getHunt().getId()));
		}
	}

	private static Answer formToAnswer(forms.Enigma form) {
		Answer answer = null;
		if (form.answer.type == forms.Enigma.Answer.TextAnswer) {
			models.TextAnswer a = new models.TextAnswer();
			Set<String> labels = new HashSet<String>();
			for (String label: form.answer.possibleTexts) {
				labels.add(label);
			}
			a.setLabels(labels);
			answer = a;
		} else {
			models.GeolocatedAnswer a = new models.GeolocatedAnswer();
			a.setPosition(models.Position.createFrom(form.answer.position, form.answer.accuracy, form.answer.labelPosition));
			answer = a;
		}
		
		return answer;
	}

	private static Set<Clue> formToClues(forms.Enigma form) {
		Set<Clue> clues = new HashSet<Clue>();
		if (form.clues == null) {
			return clues;
		}
		
		int index = 1;
		for (forms.Enigma.Clue c: form.clues) {
			if (c.type == forms.Enigma.Clue.TextClue) {
				models.TextClue clue = new models.TextClue();
				clue.setDescription(c.textDescription);
				clue.setNumber(index);
				clues.add(clue);
			} else {
				models.FileClue clue = new models.FileClue();
				try {
					clue.setFile(new URI(c.fileLink));
					clue.setDescription(c.fileDescription);
					clue.setNumber(index);
					clues.add(clue);
				} catch (URISyntaxException e) {
					// Ne rien faire...
				}
			}
			index++;
		}
		
		return clues;
	}

	private static models.Enigma formToEnigma(forms.Enigma form) {
		models.Enigma e = new models.Enigma();
		e.setDescription(form.description);

		return e;
	}
	
	public static Result showXML(String eid) {
		ObjectConnection oc = Sesame.getObjectConnection();
		StringWriter str = new StringWriter();
		try {
			RDFXMLWriter writer = new RDFXMLWriter(str);
			String queryString = "DESCRIBE <" + models.Enigma.URI + eid + ">";
			oc.prepareGraphQuery(QueryLanguage.SPARQL, queryString).evaluate(writer);
		} catch (Exception e) {
			System.out.println("Exception : " + e);
			return notFound();
		}
		return ok(str.toString());
	}
	
	public static Result showTurtle(String eid) {
		ObjectConnection oc = Sesame.getObjectConnection();
		StringWriter str = new StringWriter();
		try {
			TurtleWriter writer = new TurtleWriter(str);
			String queryString = "DESCRIBE <" + models.Enigma.URI + eid + ">";
			oc.prepareGraphQuery(QueryLanguage.SPARQL, queryString).evaluate(writer);
		} catch (Exception e) {
			System.out.println("Exception : " + e);
			return notFound();
		}
		return ok(str.toString());
	}

	public static Result edit(String eid) {
		return ok();
	}

	public static Result delete(String eid) {
		return ok();
	}

	public static Result update(String eid) {
		return ok();
	}

	public static Result submitUpdateForm(String eid) {
		return ok();
	}
	
	public static Result showClueXML(String cid) {
		ObjectConnection oc = Sesame.getObjectConnection();
		StringWriter str = new StringWriter();
		try {
			RDFXMLWriter writer = new RDFXMLWriter(str);
			String queryString = "DESCRIBE <" + models.Clue.URI + cid + ">";
			oc.prepareGraphQuery(QueryLanguage.SPARQL, queryString).evaluate(writer);
		} catch (Exception e) {
			System.out.println("Exception : " + e);
			return notFound();
		}
		return ok(str.toString());
	}
	
	public static Result showClueTurtle(String cid) {
		ObjectConnection oc = Sesame.getObjectConnection();
		StringWriter str = new StringWriter();
		try {
			TurtleWriter writer = new TurtleWriter(str);
			String queryString = "DESCRIBE <" + models.Clue.URI + cid + ">";
			oc.prepareGraphQuery(QueryLanguage.SPARQL, queryString).evaluate(writer);
		} catch (Exception e) {
			System.out.println("Exception : " + e);
			return notFound();
		}
		return ok(str.toString());
	}
	
	public static Result showAnswerXML(String aid) {
		ObjectConnection oc = Sesame.getObjectConnection();
		StringWriter str = new StringWriter();
		try {
			RDFXMLWriter writer = new RDFXMLWriter(str);
			String queryString = "DESCRIBE <" + models.Answer.URI + aid + ">";
			oc.prepareGraphQuery(QueryLanguage.SPARQL, queryString).evaluate(writer);
		} catch (Exception e) {
			System.out.println("Exception : " + e);
			return notFound();
		}
		return ok(str.toString());
	}
	
	public static Result showAnswerTurtle(String aid) {
		ObjectConnection oc = Sesame.getObjectConnection();
		StringWriter str = new StringWriter();
		try {
			TurtleWriter writer = new TurtleWriter(str);
			String queryString = "DESCRIBE <" + models.Answer.URI + aid + ">";
			oc.prepareGraphQuery(QueryLanguage.SPARQL, queryString).evaluate(writer);
		} catch (Exception e) {
			System.out.println("Exception : " + e);
			return notFound();
		}
		return ok(str.toString());
	}

}
