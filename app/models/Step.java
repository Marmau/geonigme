package models;

import java.util.Set;

import org.openrdf.annotations.Iri;
import org.openrdf.annotations.Sparql;
import org.openrdf.model.Resource;
import org.openrdf.repository.object.ObjectConnection;
import org.openrdf.repository.object.RDFObject;

@Iri(NS.GNGM + "Step")
public class Step implements RDFObject {

	public static final String URI = "http://geonigme.fr/step/";

//	private Set<Enigma> enigmas;
	private Hunt stepOfHunt;
	private String description;
	private Position position;

	@Iri(NS.GNGM + "description")
	public String getDescription() {
		return description;
	}

	@Iri(NS.GNGM + "description")
	public void setDescription(String description) {
		this.description = description;
	}

	@Sparql(NS.PREFIX + "SELECT ?enigma { ?enigma gngm:enigmaOfStep $this }")
	public Set<Enigma> getEnigmas() {
		return null;
	}
		
//	@Iri(URIs.GNGM + "enigma")
//	public Set<Enigma> getEnigmas() {
//		return enigmas;
//	}

//	@Iri(URIs.GNGM + "enigma")
//	public void setEnigmas(Set<Enigma> enigmas) {
//		this.enigmas = enigmas;
//	}

	@Iri(NS.GNGM + "stepOfHunt")
	public Hunt getHunt() {
		return stepOfHunt;
	}

	@Iri(NS.GNGM + "stepOfHunt")
	public void setHunt(Hunt stepOfHunt) {
		this.stepOfHunt = stepOfHunt;
	}

	@Iri(NS.GNGM + "position")
	public Position getPosition() {
		return position;
	}

	@Iri(NS.GNGM + "position")
	public void setPosition(Position position) {
		this.position = position;
	}

	public String getId() {
		return getResource().stringValue().replace(URI, "");
	}

	@Override
	public ObjectConnection getObjectConnection() {
		return null;
	}

	@Override
	public Resource getResource() {
		return null;
	}
}
