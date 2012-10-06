package models.old;

import gngm.Enigma;

public class Clue extends Resource implements gngm.Clue {

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
