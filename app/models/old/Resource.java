package models.old;

import geo.SpatialThing;

import java.util.HashSet;
import java.util.Set;

import javax.xml.datatype.XMLGregorianCalendar;

import tags.Tagging;

public abstract class Resource implements rdfs.Resource {
	private XMLGregorianCalendar createdAt;
	private String description;
	private Integer number;
	private Set<String> labels;
	private Set<Object> sameAs;
	
	public void setOneLabel(String label) {
		Set<String> labels = new HashSet<String>();
		labels.add(label);
		this.setLabels(labels);
	}
	
	public String getOneLabel() {
		if (labels.iterator().hasNext())
			return (String)labels.iterator().next();
		
		return null;
	}
	
	public void addLabel(String label) {
		labels.add(label);
	}
	

	public XMLGregorianCalendar getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(XMLGregorianCalendar createdAt) {
		this.createdAt = createdAt;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;		
	}


	public Integer getNumber() {
		return number;
	}


	public void setNumber(Integer number) {
		this.number = number;		
	}


	public Set<String> getLabels() {
		return labels;
	}


	public void setLabels(Set<? extends String> labels) {
		this.labels = (Set<String>) labels;
	}	
	

	public Set<Object> getSameAs() {
		return sameAs;
	}


	public void setSameAs(Set<?> sameAs) {
		this.sameAs = (Set<Object>) sameAs;
	}


	public Set<Tagging> getTags() {
		return null;
	}


	public void setTags(Set<? extends Tagging> tags) {
	}
	

	public Set<Object> getDates() {
		return null;
	}


	public void setDates(Set<?> dates) {
	}


	public Set<Object> getTitles() {
		return null;
	}


	public void setTitles(Set<?> titles) {
	}


	public Set<Object> getLat_longs() {
		return null;
	}


	public void setLat_longs(Set<?> lat_longs) {
	}


	public Set<SpatialThing> getLocations() {
		return null;
	}


	public void setLocations(Set<? extends SpatialThing> locations) {
	}


	public Set<Object> getSemanticRelation() {
		return null;
	}


	public void setSemanticRelation(Set<?> semanticRelation) {		
	}


	public Set<Object> getSubjects() {
		return null;
	}


	public void setSubjects(Set<?> subjects) {		
	}


	public Set<Object> getBased_near() {
		return null;
	}
	

	public void setBased_near(Set<?> based_near) {
	}

}
