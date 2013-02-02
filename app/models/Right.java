package models;

public enum Right {
	USER_LIST,
	USER_EDIT;
	
	private final int value;
	
	/*
	private static int next;
	static {
		next = 1;
        for (Right foo : EnumSet.allOf(Right.class)) {
            foo.value = next;
            next *= 2;
        }
	}
	*/
	Right() {
		this.value = 1 << this.ordinal();
        //this.value = 
    }

	public int getValue() {
		return value;
	}
}
