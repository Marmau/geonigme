package models;

import gngm.Enigma;

import org.openrdf.annotations.Iri;

@Iri("http://geonigme.fr/rdf/ontology#Clue")
public class Clue {
	
	private Enigma clueOfEnigma;
	
	@Iri("http://geonigme.fr/rdf/ontology#clueOfEnigma")
	Enigma getClueOfEnigma() {
		return clueOfEnigma;
	}
	@Iri("http://geonigme.fr/rdf/ontology#clueOfEnigma")
	void setClueOfEnigma(Enigma clueOfEnigma) {
		this.clueOfEnigma = clueOfEnigma;
	}

}
