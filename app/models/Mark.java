package models;

import org.openrdf.annotations.Iri;

@Iri(NS.GNGM + "Mark")
public class Mark {

	private Float value;

	@Iri(NS.GNGM + "value")
	Float getValue() {
		return value;
	}

	@Iri(NS.GNGM + "value")
	void setValue(Float value) {
		this.value = value;
	}
}
