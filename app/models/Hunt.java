package models;

import global.Sesame;

import java.util.List;
import java.util.Set;

import javax.xml.datatype.XMLGregorianCalendar;

import org.openrdf.annotations.Iri;
import org.openrdf.annotations.Sparql;
import org.openrdf.model.Resource;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.object.ObjectConnection;
import org.openrdf.repository.object.RDFObject;

@Iri(NS.GNGM + "Hunt")
public class Hunt implements RDFObject {

	public static final String URI = "http://geonigme.fr/hunt/";

	private Integer level;
	private Area area;
	private User createdBy;
	private boolean published;
	private Set<Mark> marks;
	private Set<Tag> tags;
	private String language;

	private XMLGregorianCalendar createdAt;
	private XMLGregorianCalendar modifiedAt;
	private String description;
	private String label;

	@Iri(NS.GNGM + "createdAt")
	public XMLGregorianCalendar getCreatedAt() {
		return createdAt;
	}

	@Iri(NS.GNGM + "createdAt")
	public void setCreatedAt(XMLGregorianCalendar createdAt) {
		this.createdAt = createdAt;
	}

	@Iri(NS.GNGM + "modifiedAt")
	public XMLGregorianCalendar getModifiedAt() {
		return modifiedAt;
	}

	@Iri(NS.GNGM + "modifiedAt")
	public void setModifiedAt(XMLGregorianCalendar modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

	@Iri(NS.RDFS + "label")
	public String getLabel() {
		return label;
	}

	@Iri(NS.RDFS + "label")
	public void setLabel(String label) {
		this.label = label;
	}

	@Iri(NS.GNGM + "description")
	public String getDescription() {
		return description;
	}

	@Iri(NS.GNGM + "description")
	public void setDescription(String description) {
		this.description = description;
	}

	@Iri(NS.GNGM + "area")
	public Area getArea() {
		return area;
	}

	@Iri(NS.GNGM + "area")
	public void setArea(Area area) {
		this.area = area;
	}

	@Iri(NS.GNGM + "createdBy")
	public User getCreatedBy() {
		return createdBy;
	}

	@Iri(NS.GNGM + "createdBy")
	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public String getStringLevel() {
		Integer level = getLevel();
		if (level == 0) {
			return "Facile";
		} else if (level == 1) {
			return "Intermédiaire";
		} else {
			return "Difficile";
		}
	}

	@Iri(NS.GNGM + "level")
	public Integer getLevel() {
		return level;
	}

	@Iri(NS.GNGM + "level")
	public void setLevel(Integer level) {
		this.level = level;
	}

	@Iri(NS.GNGM + "mark")
	public Set<Mark> getMarks() {
		return marks;
	}

	@Iri(NS.GNGM + "mark")
	public void setMarks(Set<Mark> marks) {
		this.marks = marks;
	}

	@Iri(NS.GNGM + "published")
	public Boolean isPublished() {
		return published;
	}

	@Iri(NS.GNGM + "published")
	public void setPublished(Boolean published) {
		this.published = published;
	}

	@Sparql(NS.PREFIX + "SELECT ?step { ?step gngm:stepOfHunt $this. ?step gngm:number ?number } ORDER BY ?number")
	public List<Step> getSteps() {
		return null;
	}

	@Sparql(NS.PREFIX + "SELECT ?enigma { " + "?step gngm:stepOfHunt $this. " + "?step gngm:number ?stepNumber. "
			+ "?enigma gngm:enigmaOfStep ?step. " + "?enigma gngm:number ?enigmaNumber "
			+ "} ORDER BY ?stepNumber ?enigmaNumber")
	public List<Enigma> getEnigmas() {
		return null;
	}

	@Iri(NS.TAGS + "taggedWithTag")
	public Set<Tag> getTags() {
		return tags;
	}

	@Iri(NS.TAGS + "taggedWithTag")
	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}
	
	@Iri(NS.GNGM + "language")
	public String getLanguage() {
		return language;
	}

	@Iri(NS.GNGM + "language")
	public void setLanguage(String language) {
		this.language = language;
	}

	public String getId() {
		return getResource().stringValue().replace(URI, "");
	}

	public void save() throws RepositoryException {
		ObjectConnection oc = Sesame.getObjectConnection();
		oc.addObject(Hunt.URI + getId(), this);
	}
	
	public void delete() throws RepositoryException {
		ObjectConnection oc = Sesame.getObjectConnection();
		oc.removeDesignation(this, URI + getId());
		//getEnigmas
	}

	@Override
	public ObjectConnection getObjectConnection() {
		return null;
	}

	@Override
	public Resource getResource() {
		return null;
	}
}
