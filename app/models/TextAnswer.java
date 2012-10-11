package models;

import org.openrdf.annotations.Iri;
import rdfs.subClassOf;

@subClassOf({"http://geonigme.fr/rdf/ontology#Answer"})
@Iri("http://geonigme.fr/rdf/ontology#TextAnswer")
public class TextAnswer extends Answer {
	
	private String text;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	

}
