package models.old;

import gngm.Enigma;

public class Answer extends Resource implements gngm.Answer {

	private gngm.Enigma answerOfEnigma;

	@Override
	public Enigma getAnswerOfEnigma() {
		return answerOfEnigma;
	}

	@Override
	public void setAnswerOfEnigma(Enigma answerOfEnigma) {
		this.answerOfEnigma = answerOfEnigma;

	}

}
