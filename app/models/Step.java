package models;

import gngm.Enigma;
import gngm.Hunt;

import java.util.Set;

public class Step implements gngm.Step {
	
	Set<gngm.Enigma> enigmas;
	Hunt hunt;

	@Override
	public Set<Enigma> getEnigmas() {
		return enigmas;
	}

	@Override
	public void setEnigmas(Set<? extends Enigma> enigmas) {
		this.enigmas=(Set<gngm.Enigma>) enigmas;
		
	}

	@Override
	public Hunt getStepOfHunt() {
		return hunt;
	}

	@Override
	public void setStepOfHunt(Hunt stepOfHunt) {
		this.hunt=hunt;
		
	}

}
