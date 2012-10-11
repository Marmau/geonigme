package models;

import org.openrdf.annotations.Iri;
import rdfs.subClassOf;

@subClassOf({"http://www.w3.org/2000/01/rdf-schema#Resource"})
@Iri("http://geonigme.fr/rdf/ontology#Mark")
public class Mark {

	private Float value;
	
	@Iri("http://geonigme.fr/rdf/ontology#value")
	Float getValue() {
		return value;
	}
	@Iri("http://geonigme.fr/rdf/ontology#value")
	void setValue(Float value) {
		this.value = value;
	}
}
