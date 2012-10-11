package models;

import java.util.Set;

import org.openrdf.annotations.Iri;

@Iri("http://geonigme.fr/rdf/ontology#Enigma")
public class Enigma {
	
	private Set<? extends Answer> answers;
	private Set<? extends Clue> clues;
	private Step enigmaOfStep;
	
	@Iri("http://geonigme.fr/rdf/ontology#answer")
	Set<? extends Answer> getAnswers() {
		return answers;
	}
	@Iri("http://geonigme.fr/rdf/ontology#answer")
	void setAnswers(Set<? extends Answer> answers) {
		this.answers = answers;
	}

	@Iri("http://geonigme.fr/rdf/ontology#clue")
	Set<? extends Clue> getClues() {
		return clues;
	}
	@Iri("http://geonigme.fr/rdf/ontology#clue")
	void setClues(Set<? extends Clue> clues) {
		this.clues = clues;
	}

	@Iri("http://geonigme.fr/rdf/ontology#enigmaOfStep")
	Step getEnigmaOfStep() {
		return enigmaOfStep;
	}
	
	@Iri("http://geonigme.fr/rdf/ontology#enigmaOfStep")
	void setEnigmaOfStep(Step enigmaOfStep) {
		this.enigmaOfStep = enigmaOfStep;
	}
}
