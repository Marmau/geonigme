package models;

import java.util.HashSet;
import java.util.Set;
import org.openrdf.annotations.Iri;
import rdfs.subClassOf;
import rdfs.subPropertyOf;

@subClassOf({"http://www.w3.org/2004/02/skos/core#Concept"})
@Iri("http://www.holygoat.co.uk/owl/redwood/0.1/tags/Tag")
public class Tag {
	
	private String name;
	private Set<Hunt> isTagOf;
	
	/** Indicates that the subject tag applies to the object resource. This does not assert by who, when, or why the tagging occurred. For that information, use a reified Tagging resource. */
	@Iri("http://www.holygoat.co.uk/owl/redwood/0.1/tags/isTagOf")
	public Set<? extends Object> getIsTagOf() {
		return isTagOf;
	}
	
	/** Indicates that the subject tag applies to the object resource. This does not assert by who, when, or why the tagging occurred. For that information, use a reified Tagging resource. */
	@Iri("http://www.holygoat.co.uk/owl/redwood/0.1/tags/isTagOf")
	public void setIsTagOf(Set<Hunt> isTagOf) {
		this.isTagOf = isTagOf;
	}

	/** The name of a tag. Note that we can't relate this to skos:prefLabel because we cannot guarantee that tags have unique labels in a given conceptual scheme. Or can we? */
	@subPropertyOf({"http://purl.org/dc/elements/1.1/title", "http://www.w3.org/2000/01/rdf-schema#label"})
	@Iri("http://www.holygoat.co.uk/owl/redwood/0.1/tags/name")
	public String getName() {
		return name;
	}
	
	/** The name of a tag. Note that we can't relate this to skos:prefLabel because we cannot guarantee that tags have unique labels in a given conceptual scheme. Or can we? */
	@subPropertyOf({"http://purl.org/dc/elements/1.1/title", "http://www.w3.org/2000/01/rdf-schema#label"})
	@Iri("http://www.holygoat.co.uk/owl/redwood/0.1/tags/name")
	public void setName(String name) {
		this.name = name;
	}
	
	public static Set<Tag> fromString(String string) {
		Set<Tag> tags = new HashSet<Tag>();
		for (String nameTag : string.split(",")) {
			Tag tag = new Tag();
			tag.setName(nameTag.trim());
			tags.add(tag);
		}
		
		return tags;
	}
}
