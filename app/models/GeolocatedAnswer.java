package models;

import java.util.Set;

import javassist.tools.rmi.ObjectNotFoundException;

import gngm.Enigma;

public class GeolocatedAnswer implements gngm.GeolocatedAnswer, geo.SpatialThing {

	private Enigma answerOfEnigma;
	private String lat;
	private String _long;

	@Override
	public Enigma getAnswerOfEnigma() {
		return answerOfEnigma;
	}

	@Override
	public void setAnswerOfEnigma(Enigma answerOfEnigma) {
		this.answerOfEnigma = answerOfEnigma;

	}

	@Override
	public Set<Object> getAlts() {
		return null;
	}

	@Override
	public void setAlts(Set<?> alts) {
		try {
			throw new ObjectNotFoundException("Methode nexiste pas");
		} catch (ObjectNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	@Override
	public String getLat() {
		return lat;
	}

	@Override
	public void setLat(String lat) {
		this.lat=lat;
		
	}

	@Override
	public String getLong() {
		return _long;
	}

	@Override
	public void setLong(String _long) {
		this._long=_long;
		
	}

}
