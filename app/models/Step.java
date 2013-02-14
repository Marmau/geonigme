package models;

import global.Sesame;

import java.util.List;

import org.openrdf.annotations.Iri;
import org.openrdf.annotations.Sparql;
import org.openrdf.model.Resource;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.object.ObjectConnection;
import org.openrdf.repository.object.RDFObject;

@Iri(NS.GNGM + "Step")
public class Step implements RDFObject {

	public static final String URI = "http://geonigme.fr/step/";

	private Hunt stepOfHunt;
	private String description;
	private Position position;
	private Integer number;

	@Iri(NS.GNGM + "number")
	public Integer getNumber() {
		return number;
	}

	@Iri(NS.GNGM + "number")
	public void setNumber(Integer number) {
		this.number = number;
	}

	@Iri(NS.GNGM + "description")
	public String getDescription() {
		return description;
	}

	@Iri(NS.GNGM + "description")
	public void setDescription(String description) {
		this.description = description;
	}

	@Sparql(NS.PREFIX + "SELECT ?step { " + "$this gngm:stepOfHunt ?hunt. " + "$this gngm:number ?number. "
			+ "?step gngm:stepOfHunt ?hunt. " + "?step gngm:number ?nextNumber." + "FILTER (?number + 1 = ?nextNumber)"
			+ "}")
	public Step getNextStep() {
		return null;
	}

	@Sparql(NS.PREFIX
			+ "SELECT ?enigma { ?enigma gngm:enigmaOfStep $this. ?enigma gngm:number ?number } ORDER BY ?number")
	public List<Enigma> getEnigmas() {
		return null;
	}

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

	public void delete() throws RepositoryException {
		ObjectConnection oc = Sesame.getObjectConnection();
		setDescription(null);
		setHunt(null);
		setNumber(null);
		setPosition(null);
		oc.removeDesignation(this, URI + getId());
	}
	
}
