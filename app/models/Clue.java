package models;

import global.Sesame;

import org.codehaus.jackson.node.ObjectNode;
import org.openrdf.annotations.Iri;
import org.openrdf.annotations.Sparql;
import org.openrdf.model.Resource;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.object.ObjectConnection;
import org.openrdf.repository.object.RDFObject;

import play.libs.Json;

@Iri(NS.GNGM + "Clue")
public class Clue implements RDFObject {

	public static final String URI = "http://geonigme.fr/clue/";

	private Enigma clueOfEnigma;
	private Integer number;
	private String description;

	@Iri(NS.GNGM + "description")
	public String getDescription() {
		return description;
	}

	@Iri(NS.GNGM + "description")
	public void setDescription(String label) {
		this.description = label;
	}

	@Iri(NS.GNGM + "clueOfEnigma")
	public Enigma getEnigma() {
		return clueOfEnigma;
	}

	@Iri(NS.GNGM + "clueOfEnigma")
	public void setEnigma(Enigma clueOfEnigma) {
		this.clueOfEnigma = clueOfEnigma;
	}

	@Iri(NS.GNGM + "number")
	public Integer getNumber() {
		return number;
	}

	@Iri(NS.GNGM + "number")
	public void setNumber(Integer number) {
		this.number = number;
	}

	@Sparql(NS.PREFIX + "SELECT ?clue { " + "$this gngm:clueOfEnigma ?enigma. " + "$this gngm:number ?number. "
			+ "?clue gngm:clueOfEnigma ?enigma. " + "?clue gngm:number ?nextNumber."
			+ "FILTER (?number + 1 = ?nextNumber)" + "}")
	public Clue getNextClue() {
		return null;
	}

	public String getId() {
		return getResource().stringValue().replace(URI, "");
	}

	public void reset() {
		this.setDescription(null);
		this.setEnigma(null);
		this.setNumber(null);
	}

	public ObjectNode toJson() {
		ObjectNode result = Json.newObject();

		result.put("id", getId());
		result.put("number", getNumber());
		result.put("description", getDescription());

		return result;
	}
	
	public void delete() throws RepositoryException {
		ObjectConnection oc = Sesame.getObjectConnection();
		setDescription(null);
		setEnigma(null);
		setNumber(null);
		oc.removeDesignation(this, URI + getId());
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
