package models;

import org.openrdf.annotations.Iri;

@Iri(NS.GNGM + "Area")
public class Area {
	private Point firstPoint;
	private Point secondPoint;

	@Iri(NS.GNGM + "firstPoint")
	public Point getFirstPoint() {
		return firstPoint;
	}

	@Iri(NS.GNGM + "firstPoint")
	public void setFirstPoint(Point firstPoint) {
		this.firstPoint = firstPoint;
	}

	@Iri(NS.GNGM + "secondPoint")
	public Point getSecondPoint() {
		return secondPoint;
	}

	@Iri(NS.GNGM + "secondPoint")
	public void setSecondPoint(Point secondPoint) {
		this.secondPoint = secondPoint;
	}

	public static Area createFrom(String string) {
		Area result = new Area();
		String[] stringPoints = string.split("\\|");

		result.setFirstPoint(Point.createFrom(stringPoints[0]));
		result.setSecondPoint(Point.createFrom(stringPoints[1]));

		return result;
	}

	public String toTemplateString() {
		return getFirstPoint().toTemplateString() + "|" + getSecondPoint().toTemplateString();
	}
}
