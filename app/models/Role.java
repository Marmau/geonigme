package models;

import org.openrdf.annotations.Iri;
import org.openrdf.model.Resource;
import org.openrdf.repository.object.ObjectConnection;
import org.openrdf.repository.object.RDFObject;

@Iri(NS.GNGM + "Role")
public class Role implements RDFObject {

	public static final String URI = "http://geonigme.fr/role/";
	
	public static final Role MEMBER = new Role("Membre");
	public static final Role MODERATOR = new Role("Modérateur");
	public static final Role ADMINISTRATOR = new Role("Administrateur", Right.allRights());
	public static final Role DEVELOPER = new Role("Développeur", Right.allRights());
	
	static {
		MODERATOR.addRight(Right.USER_LIST);
	}

	private String name;
	private int rights;
	
	public Role(String name, int rights) {
		this.name = name;
		this.rights = rights;
	}
	public Role(String name) {
		this(name, 0);
	}
	public Role(Role other) {
		this(other.getName(), other.getRights());
	}
	
	public boolean canDo(Right r) {
		return canDo(r.getValue());
	}
	public boolean canDo(int r) {
		return true;
		//Every body is admin
		//return (rights & r) == r;
	}

	@Iri(NS.GNGM + "name")
	public String getName() {
		return name;
	}
	
	public Role addRight(Right r) {
		rights |= r.getValue();
		return this;
	}

	@Iri(NS.GNGM + "rights")
	public int getRights() {
		return rights;
	}

	public boolean hasRights() {
		return rights > 0;
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
