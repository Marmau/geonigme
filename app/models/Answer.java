package models;

import org.openrdf.annotations.Iri;

@Iri("http://geonigme.fr/rdf/ontology#Answer")
public class Answer {
	
	private Enigma answerOfEnigma;
	
	@Iri("http://geonigme.fr/rdf/ontology#answerOfEnigma")
	Enigma getAnswerOfEnigma() {
		return answerOfEnigma;
	}
	
	@Iri("http://geonigme.fr/rdf/ontology#answerOfEnigma")
	void setAnswerOfEnigma(Enigma answerOfEnigma) {
		this.answerOfEnigma = answerOfEnigma;
	}

}
