package models;

import gngm.Enigma;
import gngm.Hunt;

import java.util.Set;

import org.openrdf.annotations.Iri;

@Iri("http://geonigme.fr/rdf/ontology#Step")
public class Step {

	private Set<Enigma> enigmas;
	private Hunt stepOfHunt;
	private Float lat;
	private Float lng;
	private Integer id;
	private String description;
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getId(){
		return id;
	}
	
	@Iri("http://geonigme.fr/rdf/ontology#description")
	public String getDescription() {
		return description;
	}

	@Iri("http://geonigme.fr/rdf/ontology#description")
	public void setDescription(String description) {
		this.description = description;
	}

	@Iri("http://geonigme.fr/rdf/ontology#enigma")
	public Set<Enigma> getEnigmas() {
		return enigmas;
	}

	@Iri("http://geonigme.fr/rdf/ontology#enigma")
	public void setEnigmas(Set<Enigma> enigmas) {
		this.enigmas = enigmas;
	}

	@Iri("http://geonigme.fr/rdf/ontology#stepOfHunt")
	public Hunt getHunt() {
		return stepOfHunt;
	}

	@Iri("http://geonigme.fr/rdf/ontology#stepOfHunt")
	public void setHunt(Hunt stepOfHunt) {
		this.stepOfHunt = stepOfHunt;
	}
	

	@Iri("http://www.w3.org/2003/01/geo/wgs84_pos#lat")
	public Float getLat() {
		return lat;
	}

	@Iri("http://www.w3.org/2003/01/geo/wgs84_pos#lat")
	public void setLat(Float lat) {
		this.lat = lat;

	}

	@Iri("http://www.w3.org/2003/01/geo/wgs84_pos#long")
	public Float getLong() {
		return lng;
	}

	@Iri("http://www.w3.org/2003/01/geo/wgs84_pos#long")
	public void setLong(Float lng) {
		this.lng = lng;
	}
}
