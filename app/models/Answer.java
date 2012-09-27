package models;

import gngm.Enigma;

public class Answer implements gngm.Answer {

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
