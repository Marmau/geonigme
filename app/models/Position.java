package models;

import org.openrdf.annotations.Iri;

@Iri(NS.GNGM + "Position")
public class Position extends Point {
	private float accuracy;
	private String place;

	@Iri(NS.GNGM + "accuracy")
	public Float getAccuracy() {
		return accuracy;
	}

	@Iri(NS.GNGM + "accuracy")
	public void setAccuracy(Float accuracy) {
		this.accuracy = accuracy;
	}

	@Iri(NS.GNGM + "place")
	public String getPlace() {
		return place;
	}

	@Iri(NS.GNGM + "place")
	public void setPlace(String place) {
		this.place = place;
	}

	public static Position createFrom(String position, float accuracy) {
		Position result = new Position();

		result.fillFrom(position);
		result.setAccuracy(accuracy);

		return result;
	}
	
	public static Position createFrom(String position, float accuracy, String place) {
		Position result = createFrom(position, accuracy);
		result.setPlace(place);
		
		return result;
	}

}