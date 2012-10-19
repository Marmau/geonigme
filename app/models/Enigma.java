package models;

import java.util.List;

import org.openrdf.annotations.Iri;
import org.openrdf.annotations.Sparql;
import org.openrdf.model.Resource;
import org.openrdf.repository.object.ObjectConnection;
import org.openrdf.repository.object.RDFObject;

@Iri(NS.GNGM + "Enigma")
public class Enigma implements RDFObject {

	public static final String URI = "http://geonigme.fr/enigma/";

	private Step enigmaOfStep;
	private String description;
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

	@Sparql(NS.PREFIX + "SELECT ?answer { ?answer gngm:answerOfEnigma $this }")
	public Answer getAnswer() {
		return null;
	}

	@Sparql(NS.PREFIX +
		"SELECT ?clue { ?clue gngm:clueOfEnigma $this. ?clue gngm:number ?number } ORDER BY ?number")
	public List<Clue> getClues() {
		return null;
	}

	@Iri(NS.GNGM + "enigmaOfStep")
	public Step getStep() {
		return enigmaOfStep;
	}

	@Iri(NS.GNGM + "enigmaOfStep")
	public void setStep(Step enigmaOfStep) {
		this.enigmaOfStep = enigmaOfStep;
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
