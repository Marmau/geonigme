package models;

import java.util.List;
import java.util.Set;

import javax.xml.datatype.XMLGregorianCalendar;

import org.openrdf.annotations.Bind;
import org.openrdf.annotations.Iri;
import org.openrdf.annotations.Sparql;
import org.openrdf.model.Resource;
import org.openrdf.repository.object.ObjectConnection;
import org.openrdf.repository.object.RDFObject;

@Iri(NS.GNGM + "Hunt")
public class Hunt implements RDFObject {

	public static final String URI = "http://geonigme.fr/hunt/";

	public static final String RDF = "<http://www.w3.org/1999/02/22-rdf-syntax-ns#>";
	public static final String RDFS = "<http://www.w3.org/2000/01/rdf-schema#>";
	public static final String GNGM = "<http://geonigme.fr/rdf/ontology#>";

	private Integer level;
	private Area area;
	private User createdBy;
	private boolean published;
	private Set<Mark> marks;
	private Set<Tag> tags;

	private XMLGregorianCalendar createdAt;
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

	@Iri("http://www.w3.org/2000/01/rdf-schema#label")
	public String getLabel() {
		return label;
	}

	@Iri("http://www.w3.org/2000/01/rdf-schema#label")
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

	@Sparql(NS.PREFIX +
		"SELECT ?step { ?step gngm:stepOfHunt $this. ?step gngm:number ?number } ORDER BY ?number")
	public List<Step> getSteps() {
		return null;
	}

	@Iri("http://www.holygoat.co.uk/owl/redwood/0.1/tags/taggedWithTag")
	public Set<Tag> getTags() {
		return tags;
	}

	@Iri("http://www.holygoat.co.uk/owl/redwood/0.1/tags/taggedWithTag")
	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}

	// @Sparql("PREFIX gngm: " + URIs.GNGM + "\n" +
	// "SELECT ?hunt WHERE { ?hunt gngm:level $level }")
	public Set<org.openrdf.result.Result<Hunt>> getHuntsWithLevel(@Bind("level") Integer level) {
		return null;
	}

	// @Sparql("PREFIX rdf:" + RDF + "\n" +
	// "PREFIX gngm:" + URIs.GNGM + "\n" +
	// "SELECT ?s WHERE { " +
	// "?s rdf:type <" + NS +"> . " +
	// "?s  gngm:cree ?date " +
	// "} " +
	// "ORDER BY ?date " +
	// "LIMIT $number " +
	// "OFFSET $offset ")
	public Set<org.openrdf.result.Result<Hunt>> getHuntsSortByCreationDate(
			@Bind("number") Integer number, @Bind("offset") Integer offset) {
		return null;
	}

	// @Sparql("PREFIX rdf:" + RDF + "\n" +
	// "PREFIX rdfs:" + RDFS + "\n" +
	// "PREFIX gngm:" + URIs.GNGM + "\n" +
	// "SELECT ?s WHERE {" +
	// "?s rdf:type <http://geonigme.fr/rdf/ontology#Hunt> ." +
	// "?s rdfs:label ?label ." +
	// "?s gngm:cree ?date ." +
	// "?s gngm:note ?note ." +
	// "?note gngm:moyenne ?moyenne " +
	// "?s gngm:estPublie $published " +
	// "} " +
	// "ORDER BY DESC($order) " +
	// "LIMIT $number " +
	// "OFFSET $offset")
	public Set<org.openrdf.result.Result<Hunt>> getHuntsSortByParams(@Bind("order") String order,
			@Bind("number") Integer number, @Bind("offset") Integer offset,
			@Bind("published") Boolean published) {
		return null;
	}

	// @Sparql("PREFIX rdf:" + RDF + "\n" +
	// "PREFIX gngm:" + URIs.GNGM + "\n" +
	// "SELECT ?s WHERE { " +
	// "?s rdf:type <" + NS + "> . " +
	// "?s gngm:createur <{$author}> " +
	// "}")
	public Set<org.openrdf.result.Result<Hunt>> getHuntsByAuthor(@Bind("author") String author) {
		return null;
	}

	public String getId() {
		return getResource().stringValue().replace(URI, "");
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
