package models;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.openrdf.annotations.Iri;
import org.openrdf.model.Resource;
import org.openrdf.repository.object.ObjectConnection;
import org.openrdf.repository.object.RDFObject;

@Iri(NS.GNGM + "Role")
public class Role implements RDFObject {

	public static final String URI = "http://geonigme.fr/role/";
	
	private static ArrayList<Role> instances = new ArrayList<>();
	
	public static final Role MEMBER = create("member", "Membre");
	public static final Role MODERATOR = create("moderator", "Modérateur");
	public static final Role ADMINISTRATOR = create("administrator", "Administrateur", Right.allRights());
	public static final Role DEVELOPER = create("developer", "Développeur", Right.allRights());
	
	static {
		MODERATOR.grantRight(Right.USER_LIST);
	}

	private String name = "unnamedRole";
	private String label = "UnnamedRole";
	private Integer rights = 0;

	public Role() {
	}
	
	public static Role create(String name, String label, int rights) {
		Role r = new Role();
		r.setName(name);
		r.setLabel(label);
		r.setRights(rights);
		instances.add(r);
		return r;
	}
	public static Role create(String name, String label) {
		return create(name, label, 0);
	}
	/*
	public Role(Role other) {
		this(other.getLabel(), other.getRights());
	}
	*/
	
	public boolean canDo(Right r) {
		return canDo(r.getValue());
	}
	public boolean canDo(int r) {
		return true;
		//Every body is admin
		//return (getRights() & r) == r;
	}

	public boolean hasRights() {
		return true;
		//return getRights() > 0;
	}
	
	public Role grantRight(Right r) {
		setRights(getRights() | r.getValue());
		return this;
	}
	public Role revokeRight(Right r) {
		setRights(getRights() & ~r.getValue());
		return this;
	}
	
	public boolean equals(Role other) {
		return getLabel().equals(other.getLabel());
	}
	public boolean equals(Object other) {
		return equals((Role) other);
	}
	
	public static Role get(String name) {
		for( Role r : instances ) {
			if( r.getName().equals(name) ) {
				return r;
			}
		}
		return null;
	}
	
	public static List<Role> getAll() {
		return instances;
	}
	
	//Getters
	
	@Iri(NS.GNGM + "name")
	public String getName() {
		return name;
	}
	
	@Iri(NS.GNGM + "label")
	public String getLabel() {
		return label;
	}

	@Iri(NS.GNGM + "rights")
	public int getRights() {
		return rights;
	}
	
	// Setters
	
	@Iri(NS.GNGM + "name")
	public void setName(String name) {
		this.name = name;
	}

	@Iri(NS.GNGM + "label")
	public void setLabel(String label) {
		this.label = label;
	}

	@Iri(NS.GNGM + "rights")
	public void setRights(Integer rights) {
		this.rights = rights;
	}

	public String urify() {
		try {
			return URI + URLEncoder.encode(getName(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
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
