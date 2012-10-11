package models;

import geo.SpatialThing;

import java.util.Set;

import javax.xml.datatype.XMLGregorianCalendar;

import org.openrdf.annotations.Iri;

import rdfs.subPropertyOf;
import tags.Tagging;

public class Resource {

	private XMLGregorianCalendar createdAt;
	private String description;
	private Integer number;
	private Set<String> labels;
	private Set<Object> sameAs;
	private Set<Object> dates;
	private Set<Object> titles;
	private Set<? extends Tagging> tags;
	private Set<Object> lat_longs;
	private Set<? extends SpatialThing> locations; 
	private Set<Object> semanticRelation;
	private Set<Object> subjects;
	private Set<Object> based_near;
	
	@Iri("http://geonigme.fr/rdf/ontology#createdAt")
	XMLGregorianCalendar getCreatedAt() {
		return createdAt;
	}
	
	@Iri("http://geonigme.fr/rdf/ontology#createdAt")
	void setCreatedAt(XMLGregorianCalendar createdAt) {
		this.createdAt = createdAt;
	}

	@Iri("http://geonigme.fr/rdf/ontology#description")
	String getDescription() {
		return description;
	}
	
	@Iri("http://geonigme.fr/rdf/ontology#description")
	void setDescription(String description) {
		this.description = description;
	}

	@Iri("http://geonigme.fr/rdf/ontology#number")
	Integer getNumber() {
		return number;
	}
	
	@Iri("http://geonigme.fr/rdf/ontology#number")
	void setNumber(Integer number) {
		this.number = number;
	}

	@Iri("http://purl.org/dc/elements/1.1/date")
	Set<Object> getDates() {
		return dates;
	}
	
	@Iri("http://purl.org/dc/elements/1.1/date")
	void setDates(Set<Object> dates) {
		this.dates = dates;
	}

	@Iri("http://purl.org/dc/elements/1.1/title")
	Set<Object> getTitles() {
		return titles;
	}
	
	@Iri("http://purl.org/dc/elements/1.1/title")
	void setTitles(Set<Object> titles) {
		this.titles = titles;
	}

	/** The relationship between a resource and a Tagging. Note, of course, that this allows us to tag tags and taggings themselves... */
	@Iri("http://www.holygoat.co.uk/owl/redwood/0.1/tags/tag")
	Set<? extends Tagging> getTags() {
		return tags;
	}
	
	/** The relationship between a resource and a Tagging. Note, of course, that this allows us to tag tags and taggings themselves... */
	@Iri("http://www.holygoat.co.uk/owl/redwood/0.1/tags/tag")
	void setTags(Set<? extends Tagging> tags) {
		this.tags = tags;
	}

	@Iri("http://www.w3.org/2000/01/rdf-schema#label")
	Set<String> getLabels() {
		return labels;
	}
	
	@Iri("http://www.w3.org/2000/01/rdf-schema#label")
	void setLabels(Set<String> labels) {
		this.labels = labels;
	}

	@Iri("http://www.w3.org/2002/07/owl#sameAs")
	Set<Object> getSameAs() {
		return sameAs;
	}
	
	@Iri("http://www.w3.org/2002/07/owl#sameAs")
	void setSameAs(Set<Object> sameAs) {
		this.sameAs = sameAs;
	}

	/** A comma-separated representation of a latitude, longitude coordinate. */
	@Iri("http://www.w3.org/2003/01/geo/wgs84_pos#lat_long")
	Set<Object> getLat_longs() {
		return lat_longs;
	}
	
	/** A comma-separated representation of a latitude, longitude coordinate. */
	@Iri("http://www.w3.org/2003/01/geo/wgs84_pos#lat_long")
	void setLat_longs(Set<Object> lat_longs) {
		this.lat_longs = lat_longs;
	}

	/** 
	 * The relation between something and the point, 
	 *  or other geometrical thing in space, where it is.  For example, the realtionship between
	 *  a radio tower and a Point with a given lat and long.
	 *  Or a relationship between a park and its outline as a closed arc of points, or a road and
	 *  its location as a arc (a sequence of points).
	 *  Clearly in practice there will be limit to the accuracy of any such statement, but one would expect
	 *  an accuracy appropriate for the size of the object and uses such as mapping .
	 */
	@subPropertyOf({"http://xmlns.com/foaf/0.1/based_near"})
	@Iri("http://www.w3.org/2003/01/geo/wgs84_pos#location")
	Set<? extends SpatialThing> getLocations() {
		return locations;
	}
	
	/** 
	 * The relation between something and the point, 
	 *  or other geometrical thing in space, where it is.  For example, the realtionship between
	 *  a radio tower and a Point with a given lat and long.
	 *  Or a relationship between a park and its outline as a closed arc of points, or a road and
	 *  its location as a arc (a sequence of points).
	 *  Clearly in practice there will be limit to the accuracy of any such statement, but one would expect
	 *  an accuracy appropriate for the size of the object and uses such as mapping .
	 */
	@subPropertyOf({"http://xmlns.com/foaf/0.1/based_near"})
	@Iri("http://www.w3.org/2003/01/geo/wgs84_pos#location")
	void setLocations(Set<? extends SpatialThing> locations) {
		this.locations = locations;
	}

	@Iri("http://www.w3.org/2004/02/skos/core#semanticRelation")
	Set<Object> getSemanticRelation() {
		return semanticRelation;
	}
	
	@Iri("http://www.w3.org/2004/02/skos/core#semanticRelation")
	void setSemanticRelation(Set<Object> semanticRelation) {
		this.semanticRelation = semanticRelation;
	}

	@Iri("http://www.w3.org/2004/02/skos/core#subject")
	Set<Object> getSubjects() {
		return subjects;
	}
	
	@Iri("http://www.w3.org/2004/02/skos/core#subject")
	void setSubjects(Set<Object> subjects) {
		this.subjects = subjects;
	}

	@Iri("http://xmlns.com/foaf/0.1/based_near")
	Set<Object> getBased_near() {
		return based_near;
	}
	
	@Iri("http://xmlns.com/foaf/0.1/based_near")
	void setBased_near(Set<Object> based_near) {
		this.based_near = based_near;
	}
	
}
