package models;

import gngm.Enigma;

public class Clue implements gngm.Clue {

	Enigma clueOfEnigma;

	@Override
	public Enigma getClueOfEnigma() {
		return clueOfEnigma;
	}

	@Override
	public void setClueOfEnigma(Enigma clueOfEnigma) {
		this.clueOfEnigma = clueOfEnigma;

	}

}
