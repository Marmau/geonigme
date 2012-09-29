package models;

import gngm.Enigma;

public class GeolocatedAnswer implements gngm.GeolocatedAnswer {

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
