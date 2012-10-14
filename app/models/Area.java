package models;

import org.openrdf.annotations.Iri;

@Iri("http://geonigme.fr/rdf/ontology#Area")
public class Area {
	private Point firstPoint;
	private Point secondPoint;

	@Iri("http://geonigme.fr/rdf/ontology#firstPoint")
	public Point getFirstPoint() {
		return firstPoint;
	}

	@Iri("http://geonigme.fr/rdf/ontology#firstPoint")
	public void setFirstPoint(Point firstPoint) {
		this.firstPoint = firstPoint;
	}

	@Iri("http://geonigme.fr/rdf/ontology#secondPoint")
	public Point getSecondPoint() {
		return secondPoint;
	}

	@Iri("http://geonigme.fr/rdf/ontology#secondPoint")
	public void setSecondPoint(Point secondPoint) {
		this.secondPoint = secondPoint;
	}
	
	public static Area fromString(String string) {
		Area result = new Area();
		String[] stringPoints = string.split("\\|");

		result.setFirstPoint(Point.fromString(stringPoints[0]));
		result.setSecondPoint(Point.fromString(stringPoints[1]));
		
		return result;
	}
	
	public String toTemplateString() {
		return getFirstPoint().toTemplateString() + "|" + getSecondPoint().toTemplateString(); 
	}
}
