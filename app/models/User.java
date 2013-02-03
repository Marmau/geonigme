package models;

import global.Sesame;

import java.util.ArrayList;
import java.util.List;

import javax.xml.datatype.XMLGregorianCalendar;

import org.openrdf.annotations.Iri;
import org.openrdf.annotations.Sparql;
import org.openrdf.model.Resource;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.object.ObjectConnection;
import org.openrdf.repository.object.ObjectQuery;
import org.openrdf.repository.object.RDFObject;

@Iri(NS.USER + "User")
public class User implements RDFObject {

	public static final String URI = "http://geonigme.fr/user/";

	private XMLGregorianCalendar inscriptionDate;
	private XMLGregorianCalendar lastLoginTime;
	private String login;
	private String mail;
	private String password;
	private Role role;

	@Sparql(NS.PREFIX +
		"SELECT ?hunt { ?hunt gngm:createdBy $this. ?hunt gngm:modifiedAt ?date } ORDER BY DESC(?date)")
	public List<Hunt> getHunts() {
		return null;
	}

	@Iri(NS.USER + "role")
	public Role getRole() {
		if( role == null ) {// Lazy loading
			setRole(Role.MEMBER);
		}
		return role;
	}

	@Iri(NS.USER + "role")
	public void setRole(Role role) {
		this.role = role;
	}

	@Iri(NS.USER + "inscriptionDate")
	public XMLGregorianCalendar getInscriptionDate() {
		return inscriptionDate;
	}

	@Iri(NS.USER + "inscriptionDate")
	public void setInscriptionDate(XMLGregorianCalendar inscriptionDate) {
		this.inscriptionDate = inscriptionDate;
	}

	@Iri(NS.USER + "lastLoginTime")
	public XMLGregorianCalendar getLastLoginTime() {
		return lastLoginTime;
	}

	@Iri(NS.USER + "lastLoginTime")
	public void setLastLoginTime(XMLGregorianCalendar lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	@Iri(NS.USER + "loginName")
	public String getLoginName() {
		return login;
	}

	@Iri(NS.USER + "loginName")
	public void setLoginName(String loginName) {
		this.login = loginName;
	}

	@Iri(NS.USER + "mail")
	public String getMail() {
		return mail;
	}

	@Iri(NS.USER + "mail")
	public void setMail(String mail) {
		this.mail = mail;
	}

	@Iri(NS.USER + "passwordSha1Hash")
	public String getPasswordSha1Hash() {
		return password;
	}

	@Iri(NS.USER + "passwordSha1Hash")
	public void setPasswordSha1Hash(String passwordSha1Hash) {
		this.password = passwordSha1Hash;
	}

	public boolean hasRights() {
		return getRole().hasRights();
	}
	
	public String getId() {
		return getResource().stringValue().replace(URI, "");
	}
	
	public void save() throws RepositoryException {
		ObjectConnection oc = Sesame.getObjectConnection();
		oc.addObject(User.URI + getId(), this);
	}

	@Override
	public ObjectConnection getObjectConnection() {
		return null;
	}

	@Override
	public Resource getResource() {
		return null;
	}
	
	public static User get(String uid) {
		ObjectConnection oc = Sesame.getObjectConnection();
		try {
			return oc.getObject(models.User.class, models.User.URI + uid);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static List<User> getAll(String orderBy) {
		List<User> users = new ArrayList<User>();
		try {
			ObjectConnection oc = Sesame.getObjectConnection();
			//users = oc.getObjects(models.User.class).asList();
			
			String sqlQuery = "SELECT ?user WHERE { ?user user:"+orderBy+" ?orderBy } ORDER BY ASC(?orderBy)";
			//System.out.println(sqlQuery);
			ObjectQuery query = oc.prepareObjectQuery(NS.PREFIX + 
				sqlQuery);
			users = query.evaluate(models.User.class).asList();
			//System.out.println("We got "+users.size()+" users");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}
	public static List<User> getAll() {
		return getAll("loginName");
	}
}
