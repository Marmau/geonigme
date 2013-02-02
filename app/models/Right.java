package models;

import org.openrdf.annotations.Iri;
import org.openrdf.model.Resource;
import org.openrdf.repository.object.ObjectConnection;
import org.openrdf.repository.object.RDFObject;

@Iri(NS.GNGM + "Right")
public enum Right implements RDFObject {
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

	@Iri(NS.GNGM + "value")
	public int getValue() {
		return value;
	}
	
	public static int allRights() {
		return -1;// ~0
	}

	@Override
	public ObjectConnection getObjectConnection() {
		return null;
	}

	@Override
	public Resource getResource() {
		return null;
	}
}
