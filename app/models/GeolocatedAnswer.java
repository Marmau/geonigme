package models;

import org.openrdf.annotations.Iri;
import rdfs.subClassOf;

@subClassOf({"http://geonigme.fr/rdf/ontology#Answer", "http://www.w3.org/2003/01/geo/wgs84_pos#SpatialThing"})
@Iri("http://geonigme.fr/rdf/ontology#GeolocatedAnswer")
public class GeolocatedAnswer extends Answer {

	private Float lat;
	private Float lng;
	
	public Float getLat() {
		return lat;
	}
	public void setLat(Float lat) {
		this.lat = lat;
	}
	public Float getLng() {
		return lng;
	}
	public void setLng(Float lng) {
		this.lng = lng;
	}
	
	
	
}
