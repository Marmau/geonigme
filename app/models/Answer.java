package models;

import org.openrdf.annotations.Iri;

@Iri(NS.GNGM + "Answer")
public class Answer {
	
	public static final String URI = "http://geonigme.fr/answer/";

	private Enigma answerOfEnigma;

	@Iri(NS.GNGM + "answerOfEnigma")
	public Enigma getEnigma() {
		return answerOfEnigma;
	}

	@Iri(NS.GNGM + "answerOfEnigma")
	public void setEnigma(Enigma answerOfEnigma) {
		this.answerOfEnigma = answerOfEnigma;
	}
	
	public void reset() {
		this.setEnigma(null);
	}
}
