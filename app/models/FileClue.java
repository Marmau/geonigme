package models;

import java.net.URI;
import org.openrdf.annotations.Iri;
import rdfs.subClassOf;

@subClassOf({"http://geonigme.fr/rdf/ontology#Clue"})
@Iri("http://geonigme.fr/rdf/ontology#FileClue")
public class FileClue extends Clue {
	
	private URI file;
	
	@Iri("http://geonigme.fr/rdf/ontology#file")
	URI getFile() {
		return file;
	}
	
	@Iri("http://geonigme.fr/rdf/ontology#file")
	void setFile(URI file) {
		this.file = file;
	}
}
