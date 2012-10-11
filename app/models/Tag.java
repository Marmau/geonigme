package models;

import java.util.Set;
import org.openrdf.annotations.Iri;
import rdfs.subClassOf;
import rdfs.subPropertyOf;

@subClassOf({"http://www.w3.org/2004/02/skos/core#Concept"})
@Iri("http://www.holygoat.co.uk/owl/redwood/0.1/tags/Tag")
public class Tag {
	
	private String name;
	private Set<Tag> equivalentTags;
	private Set<? extends Object> isTagOf;
	private Set<Tag> relatedTags;
	private Set<? extends Object> tagNames;
	
	@subPropertyOf({"http://www.w3.org/2002/07/owl#sameAs"})
	@Iri("http://www.holygoat.co.uk/owl/redwood/0.1/tags/equivalentTag")
	Set<Tag> getEquivalentTags() {
		return equivalentTags;
	}
	
	/** The two tags are asserted to be equivalent --- that is, that whenever one is associated with a resource, the other tag can be logically inferred to also be associated. Be very careful with this. I'm not sure if this should be a subproperty of owl:sameAs. */
	@subPropertyOf({"http://www.w3.org/2002/07/owl#sameAs"})
	@Iri("http://www.holygoat.co.uk/owl/redwood/0.1/tags/equivalentTag")
	void setEquivalentTags(Set<Tag> equivalentTags) {
		this.equivalentTags = equivalentTags;
	}

	/** Indicates that the subject tag applies to the object resource. This does not assert by who, when, or why the tagging occurred. For that information, use a reified Tagging resource. */
	@Iri("http://www.holygoat.co.uk/owl/redwood/0.1/tags/isTagOf")
	Set<? extends Object> getIsTagOf() {
		return isTagOf;
	}
	
	/** Indicates that the subject tag applies to the object resource. This does not assert by who, when, or why the tagging occurred. For that information, use a reified Tagging resource. */
	@Iri("http://www.holygoat.co.uk/owl/redwood/0.1/tags/isTagOf")
	void setIsTagOf(Set<?> isTagOf) {
		this.isTagOf = isTagOf;
	}

	/** The name of a tag. Note that we can't relate this to skos:prefLabel because we cannot guarantee that tags have unique labels in a given conceptual scheme. Or can we? */
	@subPropertyOf({"http://purl.org/dc/elements/1.1/title", "http://www.w3.org/2000/01/rdf-schema#label"})
	@Iri("http://www.holygoat.co.uk/owl/redwood/0.1/tags/name")
	String getName() {
		return name;
	}
	
	/** The name of a tag. Note that we can't relate this to skos:prefLabel because we cannot guarantee that tags have unique labels in a given conceptual scheme. Or can we? */
	@subPropertyOf({"http://purl.org/dc/elements/1.1/title", "http://www.w3.org/2000/01/rdf-schema#label"})
	@Iri("http://www.holygoat.co.uk/owl/redwood/0.1/tags/name")
	void setName(String name) {
		this.name = name;
	}

	/** The two tags are asserted as being related. This might be symmetric, but it certainly isn't transitive. */
	@subPropertyOf({"http://www.w3.org/2004/02/skos/core#semanticRelation"})
	@Iri("http://www.holygoat.co.uk/owl/redwood/0.1/tags/relatedTag")
	Set<Tag> getRelatedTags() {
		return relatedTags;
	}
	
	/** The two tags are asserted as being related. This might be symmetric, but it certainly isn't transitive. */
	@subPropertyOf({"http://www.w3.org/2004/02/skos/core#semanticRelation"})
	@Iri("http://www.holygoat.co.uk/owl/redwood/0.1/tags/relatedTag")
	void setRelatedTags(Set<Tag> relatedTags) {
		this.relatedTags = relatedTags;
	}

	/** The name of a tag. Note that we can't relate this to skos:prefLabel because we cannot guarantee that tags have unique labels in a given conceptual scheme. Or can we? DEPRECATED 2005-05-19: redundant 'tag'. */
	@subPropertyOf({"http://purl.org/dc/elements/1.1/title", "http://www.w3.org/2000/01/rdf-schema#label"})
	@Iri("http://www.holygoat.co.uk/owl/redwood/0.1/tags/tagName")
	Set<? extends Object> getTagNames() {
		return tagNames;
	}
	
	/** The name of a tag. Note that we can't relate this to skos:prefLabel because we cannot guarantee that tags have unique labels in a given conceptual scheme. Or can we? DEPRECATED 2005-05-19: redundant 'tag'. */
	@subPropertyOf({"http://purl.org/dc/elements/1.1/title", "http://www.w3.org/2000/01/rdf-schema#label"})
	@Iri("http://www.holygoat.co.uk/owl/redwood/0.1/tags/tagName")
	void setTagNames(Set<?> tagNames) {
		this.tagNames = tagNames;
	}

}
