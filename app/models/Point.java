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
		this.lng  = lng;
	}
	
	public static Point fromString(String string) {
		Point result = new Point();
		String[] stringCoordinates = string.split(",");
		
		result.setLat(Float.parseFloat(stringCoordinates[0]));
		result.setLng(Float.parseFloat(stringCoordinates[1]));
		
		return result;
	}
	
	public String toTemplateString() {
		return Float.toString(getLat()) + "," + Float.toString(getLng());
	}
}
