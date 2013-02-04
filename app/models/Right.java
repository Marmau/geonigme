package models;

public class Right {
	
	/*
	USER_LIST,
	USER_EDIT;
	*/
	
	private static int COUNT = 0;
	public static final Right NONE			= null;
	public static final Right MEMBER_AREA	= create();
	public static final Right USER_LIST		= create();
	public static final Right USER_EDIT		= create();
	
	
	private Integer value;

	
	public static Right create() {
		return create(1<<(COUNT++));
	}
	
	private static Right create(int value) {
		Right r = new Right();
		r.value = value;
		return r;
	}
	
	
	private Right() {
		//this.value = 1 << this.ordinal();
    }
	
	public Integer getValue() {
		return value;
	}

	public Integer v() {
		return getValue();
	}
	
	public Right AND(Right other) {
		return create(other.v() | v());
	}
	
	public static int allRights() {
		return -1;// ~0
	}
}
