package models;

import org.openrdf.annotations.Iri;

@Iri("http://www.w3.org/2003/01/geo/wgs84_pos#Point")
public class Point {
	private float lat;
	private float lng;

	/** The WGS84 latitude of a SpatialThing (decimal degrees). */
	@Iri("http://www.w3.org/2003/01/geo/wgs84_pos#lat")
	public Float getLat() {
		return lat;
	}

	/** The WGS84 latitude of a SpatialThing (decimal degrees). */
	@Iri("http://www.w3.org/2003/01/geo/wgs84_pos#lat")
	public void setLat(Float lat) {
		this.lat = lat;
	}

	/** The WGS84 longitude of a SpatialThing (decimal degrees). */
	@Iri("http://www.w3.org/2003/01/geo/wgs84_pos#long")
	public Float getLng() {
		return lng;
	}

	/** The WGS84 longitude of a SpatialThing (decimal degrees). */
	@Iri("http://www.w3.org/2003/01/geo/wgs84_pos#long")
	public void setLng(Float lng) {
		this.lng = lng;
	}

	public void fillFrom(String string) {
		String[] stringCoordinates = string.split(",");

		setLat(Float.parseFloat(stringCoordinates[0]));
		setLng(Float.parseFloat(stringCoordinates[1]));
	}

	public static Point createFrom(String point) {
		try {
			Point result = new Point();
			result.fillFrom(point);

			return result;
		} catch (Exception e) {
			return null;
		}
	}

	public float distanceTo(Point p) {
		double earthRadius = 3958.75;
		double dLat = Math.toRadians(p.getLat() - getLat());
		double dLng = Math.toRadians(p.getLng() - getLng());

		double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(Math.toRadians(getLat()))
				* Math.cos(Math.toRadians(p.getLat())) * Math.sin(dLng / 2) * Math.sin(dLng / 2);

		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		double distance = earthRadius * c;

		int meterConversion = 1609;

		return new Float(distance * meterConversion).floatValue();
	}

	public String toTemplateString() {
		return Float.toString(getLat()) + "," + Float.toString(getLng());
	}

	public boolean in(Position position) {
		return position.distanceTo(this) < position.getAccuracy();
	}
}
