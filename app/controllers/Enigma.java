package controllers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import global.Sesame;

import models.Answer;
import models.Clue;
import models.Position;

import org.openrdf.query.QueryEvaluationException;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.object.ObjectConnection;

import play.data.Form;
import play.mvc.*;

public class Enigma extends Controller {

	public static Result create(String sid) throws RepositoryException, QueryEvaluationException {
		Form<forms.Enigma> formEnigma = form(forms.Enigma.class);
		ObjectConnection oc = Sesame.getObjectConnection();

		models.Step step = oc.getObject(models.Step.class, models.Step.URI + sid);

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
			
			String eid = UUID.randomUUID().toString();
			oc.addObject(models.Enigma.URI + eid, enigma);
			
			Set<models.Clue> clues = formToClues(formEnigma.get());
			for (models.Clue c: clues) {
				c.setEnigma(enigma);
				String cid = UUID.randomUUID().toString();
				oc.addObject(models.Clue.URI + cid, c);
			}
			
			models.Answer answer = formToAnswer(formEnigma.get());
			answer.setEnigma(enigma);
			String aid = UUID.randomUUID().toString();
			oc.addObject(models.Answer.URI + aid, answer);

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
		for (forms.Enigma.Clue c: form.clues) {
			if (c.type == forms.Enigma.Clue.TextClue) {
				models.TextClue clue = new models.TextClue();
				clue.setDescription(c.textDescription);
				clues.add(clue);
			} else {
				models.FileClue clue = new models.FileClue();
				try {
					clue.setFile(new URI(c.fileLink));
					clue.setDescription(c.fileDescription);
					clues.add(clue);
				} catch (URISyntaxException e) {
					// Ne rien faire...
				}
			}
		}
		
		return clues;
	}

	private static models.Enigma formToEnigma(forms.Enigma form) {
		models.Enigma e = new models.Enigma();
		e.setDescription(form.description);

		

		return e;
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

}
