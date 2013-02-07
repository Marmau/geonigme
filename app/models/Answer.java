package models;

import org.openrdf.annotations.Iri;
import org.openrdf.model.Resource;
import org.openrdf.repository.object.ObjectConnection;
import org.openrdf.repository.object.RDFObject;

@Iri(NS.GNGM + "Answer")
public class Answer implements RDFObject {

	public static final String URI = "http://geonigme.fr/answer/";

	private Enigma answerOfEnigma;

	@Iri(NS.GNGM + "answerOfEnigma")
	public Enigma getEnigma() {
		return answerOfEnigma;
	}

	@Iri(NS.GNGM + "answerOfEnigma")
	public void setEnigma(Enigma answerOfEnigma) {
		this.answerOfEnigma = answerOfEnigma;
	}

	public String getId() {
		return getResource().stringValue().replace(URI, "");
	}

	public void reset() {
		this.setEnigma(null);
	}

	public boolean isCorrect(String answer) {
		return false;
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
