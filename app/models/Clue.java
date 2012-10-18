package models;

import org.openrdf.annotations.Iri;

@Iri(NS.GNGM + "Clue")
public class Clue {

	public static final String URI = "http://geonigme.fr/clue/";
	
	private Enigma clueOfEnigma;
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

}
