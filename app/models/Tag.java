package models;

import java.util.HashSet;
import java.util.Set;
import org.openrdf.annotations.Iri;
import org.openrdf.annotations.Sparql;

@Iri("http://www.holygoat.co.uk/owl/redwood/0.1/tags/Tag")
public class Tag {

	private String name;

	/**
	 * Indicates that the subject tag applies to the object resource. This does
	 * not assert by who, when, or why the tagging occurred. For that
	 * information, use a reified Tagging resource.
	 */
	@Sparql(NS.PREFIX +
			"SELECT ?tag { ?tag tags:isTagOf $this }")
	public Set<Hunt> getHunts() {
		return null;
	}

	/**
	 * The name of a tag. Note that we can't relate this to skos:prefLabel
	 * because we cannot guarantee that tags have unique labels in a given
	 * conceptual scheme. Or can we?
	 */
	@Iri(NS.TAGS + "name")
	public String getName() {
		return name;
	}

	/**
	 * The name of a tag. Note that we can't relate this to skos:prefLabel
	 * because we cannot guarantee that tags have unique labels in a given
	 * conceptual scheme. Or can we?
	 */
	@Iri(NS.TAGS + "name")
	public void setName(String name) {
		this.name = name;
	}

	public static Set<Tag> createFrom(String string) {
		Set<Tag> tags = new HashSet<Tag>();
		for (String nameTag : string.split(",")) {
			Tag tag = new Tag();
			tag.setName(nameTag.trim());
			tags.add(tag);
		}

		return tags;
	}
}