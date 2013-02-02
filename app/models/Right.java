package models;

import org.openrdf.annotations.Iri;
import org.openrdf.model.Resource;
import org.openrdf.repository.object.ObjectConnection;
import org.openrdf.repository.object.RDFObject;

@Iri(NS.GNGM + "Right")
public class Right implements RDFObject {
	
	private static int COUNT = 0;
	public static final Right USER_LIST = create();
	public static final Right USER_EDIT = create();
	
	private Integer value;
	
	public static Right create() {
		Right r = new Right();
		r.setValue(1<<(++COUNT));
		return r;
	}
	
	Right() {
		//this.value = 1 << this.ordinal();
        //this.value = 
    }
	
	@Iri(NS.GNGM + "value")
	public Integer getValue() {
		return value;
	}

	@Iri(NS.GNGM + "value")
	public void setValue(Integer value) {
		this.value = value;
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
