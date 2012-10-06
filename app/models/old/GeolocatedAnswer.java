package models.old;

import java.util.Set;

import gngm.Enigma;

public class GeolocatedAnswer extends Answer implements gngm.GeolocatedAnswer {

	private Enigma answerOfEnigma;
	private Float lat;
	private Float lng;

	@Override
	public Enigma getAnswerOfEnigma() {
		return answerOfEnigma;
	}

	@Override
	public void setAnswerOfEnigma(Enigma answerOfEnigma) {
		this.answerOfEnigma = answerOfEnigma;

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
