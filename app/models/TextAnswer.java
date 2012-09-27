package models;

import gngm.Enigma;

public class TextAnswer implements gngm.TextAnswer {

	Enigma answerOfEnigma;

	@Override
	public Enigma getAnswerOfEnigma() {
		return answerOfEnigma;
	}

	@Override
	public void setAnswerOfEnigma(Enigma answerOfEnigma) {
		this.answerOfEnigma = answerOfEnigma;

	}

}
