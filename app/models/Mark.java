package models;

public class Mark implements gngm.Mark {
	
	private Float value;

	@Override
	public Float getValue() {
		return value;
	}

	@Override
	public void setValue(Float value) {
		this.value=value;
		
	}

}
