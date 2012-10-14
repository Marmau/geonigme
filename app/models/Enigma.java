package models;

import java.util.Set;

import org.openrdf.annotations.Iri;

@Iri("http://geonigme.fr/rdf/ontology#Enigma")
public class Enigma {
	
	private Set<Answer> answers;
	private Set<Clue> clues;
	private Step enigmaOfStep;
	private String description;
	
	@Iri("http://geonigme.fr/rdf/ontology#description")
	public String getDescription() {
		return description;
	}

	@Iri("http://geonigme.fr/rdf/ontology#description")
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Iri("http://geonigme.fr/rdf/ontology#answer")
	public Set<Answer> getAnswers() {
		return answers;
	}
	@Iri("http://geonigme.fr/rdf/ontology#answer")
	public void setAnswers(Set<Answer> answers) {
		this.answers = answers;
	}

	@Iri("http://geonigme.fr/rdf/ontology#clue")
	public Set<? extends Clue> getClues() {
		return clues;
	}
	@Iri("http://geonigme.fr/rdf/ontology#clue")
	public void setClues(Set<Clue> clues) {
		this.clues = clues;
	}

	@Iri("http://geonigme.fr/rdf/ontology#enigmaOfStep")
	public Step getEnigmaOfStep() {
		return enigmaOfStep;
	}
	
	@Iri("http://geonigme.fr/rdf/ontology#enigmaOfStep")
	public void setEnigmaOfStep(Step enigmaOfStep) {
		this.enigmaOfStep = enigmaOfStep;
	}
}
