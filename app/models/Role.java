package models;

import org.openrdf.annotations.Iri;
import org.openrdf.model.Resource;
import org.openrdf.repository.object.ObjectConnection;
import org.openrdf.repository.object.RDFObject;

@Iri(NS.GNGM + "Step")
public class Role implements RDFObject {

	public static final String URI = "http://geonigme.fr/role/";

	private String name;
	private int rights;
	
	public boolean canDo(Right r) {
		return (rights & r.getValue()) > 0;
	}
	
	@Iri(NS.GNGM + "name")
	public String getName() {
		return name;
	}

	@Iri(NS.GNGM + "name")
	public void setName(String name) {
		this.name = name;
	}

	@Iri(NS.GNGM + "rights")
	public int getRights() {
		return rights;
	}

	@Iri(NS.GNGM + "rights")
	public void setRights(int rights) {
		this.rights = rights;
	}

	public String getId() {
		return getResource().stringValue().replace(URI, "");
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
