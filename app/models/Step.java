package models;

import gngm.Enigma;
import gngm.Hunt;

import java.util.Set;

public class Step implements gngm.Step {

	private Set<Enigma> enigmas;
	private Hunt stepOfHunt;

	@Override
	public Set<Enigma> getEnigmas() {
		return enigmas;
	}

	@Override
	public void setEnigmas(Set<? extends Enigma> enigmas) {
		this.enigmas = (Set<Enigma>) enigmas;
	}

	@Override
	public Hunt getStepOfHunt() {
		return stepOfHunt;
	}

	@Override
	public void setStepOfHunt(Hunt stepOfHunt) {
		this.stepOfHunt = stepOfHunt;
	}

}
