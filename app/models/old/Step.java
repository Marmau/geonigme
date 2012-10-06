package models.old;

import gngm.Enigma;
import gngm.Hunt;

import java.util.Set;

public class Step extends Resource implements gngm.Step {

	private Set<gngm.Enigma> enigmas;
	private gngm.Hunt stepOfHunt;
	private Float lat;
	private Float lng;

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
	

	@Override
	public Float getLat() {
		return lat;
	}

	@Override
	public void setLat(Float lat) {
		this.lat = lat;

	}

	@Override
	public Float getLong() {
		return lng;
	}

	@Override
	public void setLong(Float lng) {
		this.lng = lng;
	}

	@Override
	public Set<Object> getAlts() {
		return null;
	}
	
	@Override
	public void setAlts(Set<?> alts) {
	}

}
