package models;

import java.util.Set;

import org.openrdf.annotations.Iri;

@Iri(NS.GNGM + "Enigma")
public class Enigma {
	
	public static final String URI = "http://geonigme.fr/enigma/";

	private Set<Answer> answers;
	private Set<Clue> clues;
	private Step enigmaOfStep;
	private String description;

	@Iri(NS.GNGM + "description")
	public String getDescription() {
		return description;
	}

	@Iri(NS.GNGM + "description")
	public void setDescription(String description) {
		this.description = description;
	}

	@Iri(NS.GNGM + "answer")
	public Set<Answer> getAnswers() {
		return answers;
	}

	@Iri(NS.GNGM + "answer")
	public void setAnswers(Set<Answer> answers) {
		this.answers = answers;
	}

	@Iri(NS.GNGM + "clue")
	public Set<Clue> getClues() {
		return clues;
	}

	@Iri(NS.GNGM + "clue")
	public void setClues(Set<Clue> clues) {
		this.clues = clues;
	}

	@Iri(NS.GNGM + "enigmaOfStep")
	public Step getStep() {
		return enigmaOfStep;
	}

	@Iri(NS.GNGM + "enigmaOfStep")
	public void setStep(Step enigmaOfStep) {
		this.enigmaOfStep = enigmaOfStep;
	}
}
