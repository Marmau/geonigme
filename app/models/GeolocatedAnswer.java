package models;

import org.openrdf.annotations.Iri;

@Iri(NS.GNGM + "GeolocatedAnswer")
public class GeolocatedAnswer extends Answer {

	private Position position;
	
	@Iri(NS.GNGM + "position")
	public Position getPosition() {
		return position;
	}

	@Iri(NS.GNGM + "position")
	public void setPosition(Position position) {
		this.position = position;
	}
	
	@Override
	public void reset() {
		super.reset();
		this.setPosition(null);
	}
	
	@Override
	public boolean isCorrect(String answer) {
		Point p = Point.createFrom(answer);
		if (p == null) {
			return false;
		}
		
		return p.in(getPosition());
	}
}
