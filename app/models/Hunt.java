package models;

import gngm.Area;
import gngm.Mark;
import rdfs.subPropertyOf;

import java.util.HashSet;
import java.util.Set;

import javax.xml.datatype.XMLGregorianCalendar;

import models.old.Tag;

import org.openrdf.annotations.Bind;
import org.openrdf.annotations.Iri;
import org.openrdf.annotations.Sparql;

import user.User;

@Iri("http://geonigme.fr/rdf/ontology#Hunt")
public class Hunt {

	public static final String NS = "http://geonigme.fr/hunt/";

	private Integer level;
	private Area area;
	private User createdBy;
	private boolean published;
	private Set<Mark> marks;
	private Set<Step> steps;
	private Set<Tag> tags;

	private XMLGregorianCalendar createdAt;
	private String description;
	private String label;

	@Iri("http://geonigme.fr/rdf/ontology#createdAt")
	public XMLGregorianCalendar getCreatedAt() {
		return createdAt;
	}

	@Iri("http://geonigme.fr/rdf/ontology#createdAt")
	public void setCreatedAt(XMLGregorianCalendar createdAt) {
		this.createdAt = createdAt;
	}

	@Iri("http://www.w3.org/2000/01/rdf-schema#label")
	public String getLabel() {
		return label;
	}

	@Iri("http://www.w3.org/2000/01/rdf-schema#label")
	public void setLabel(String label) {
		this.label = label;
	}

	@Iri("http://geonigme.fr/rdf/ontology#description")
	public String getDescription() {
		return description;
	}

	@Iri("http://geonigme.fr/rdf/ontology#description")
	public void setDescription(String description) {
		this.description = description;
	}

	@Iri("http://geonigme.fr/rdf/ontology#area")
	public Area getArea() {
		return area;
	}

	@Iri("http://geonigme.fr/rdf/ontology#area")
	public void setArea(Area area) {
		this.area = area;
	}

	@Iri("http://geonigme.fr/rdf/ontology#createdBy")
	public User getCreatedBy() {
		return createdBy;
	}

	@Iri("http://geonigme.fr/rdf/ontology#createdBy")
	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	@Iri("http://geonigme.fr/rdf/ontology#level")
	public Integer getLevel() {
		return level;
	}

	@Iri("http://geonigme.fr/rdf/ontology#level")
	public void setLevel(Integer level) {
		this.level = level;
	}

	@Iri("http://geonigme.fr/rdf/ontology#mark")
	public Set<Mark> getMarks() {
		return marks;
	}

	@Iri("http://geonigme.fr/rdf/ontology#mark")
	public void setMarks(Set<Mark> marks) {
		this.marks = marks;
	}

	@Iri("http://geonigme.fr/rdf/ontology#published")
	public Boolean isPublished() {
		return published;
	}

	@Iri("http://geonigme.fr/rdf/ontology#published")
	public void setPublished(Boolean published) {
		this.published = published;
	}

	@Iri("http://geonigme.fr/rdf/ontology#step")
	public Set<Step> getSteps() {
		return steps;
	}

	public void addStep(Step step) {
		Set<Step> currentSteps = getSteps();
		if (null == currentSteps) {
			currentSteps = new HashSet<Step>();
		}

		currentSteps.add(step);
		setSteps(currentSteps);
	}

	@Iri("http://geonigme.fr/rdf/ontology#step")
	public void setSteps(Set<Step> steps) {
		this.steps = steps;
	}

	@subPropertyOf("http://www.w3.org/2004/02/skos/core#subject")
	@Iri("http://www.holygoat.co.uk/owl/redwood/0.1/tags/taggedWithTag")
	public Set<Tag> getTags() {
		return tags;
	}

	@subPropertyOf("http://www.w3.org/2004/02/skos/core#subject")
	@Iri("http://www.holygoat.co.uk/owl/redwood/0.1/tags/taggedWithTag")
	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}

	@Sparql("PREFIX gngm:<http://geonigme.fr/rdf/ontology#>\n" +
			"SELECT ?hunt WHERE { ?hunt gngm:level $level }")
	public org.openrdf.result.Result<Hunt> getHuntsWithLevel(@Bind("level") Integer level) {
		return null;
	}
}
