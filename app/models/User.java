package models;

import gngm.Hunt;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.openrdf.annotations.Bind;
import org.openrdf.annotations.Iri;
import org.openrdf.annotations.Sparql;

@Iri("http://schemas.talis.com/2005/user/schema#User")
public class User {
	
	public static final String NS = "http://geonigme.fr/user/";
	
	private String creator;
	private Date inscriptionDate;
	private Date lastLoginTime;
	private String login;
	private String mail;
	private String password;
	
	@Iri("http://geonigme.fr/rdf/ontology#creatorOf")
	public Set<Hunt> getCreatorOf() {
		// return creator;
		// ????
		Set<Hunt> plop = new HashSet<Hunt>();
		return plop;
	}
	
	@Iri("http://geonigme.fr/rdf/ontology#creatorOf")
	public void setCreatorOf(Set<? extends Hunt> creatorOf) {
		this.creator = login;
		//this.creator = "creatorOf";
	}

	@Iri("http://schemas.talis.com/2005/user/schema#inscriptionDate")
	public Date getInscriptionDate() {
		return inscriptionDate;
	}
	
	@Iri("http://schemas.talis.com/2005/user/schema#inscriptionDate")
	public void setInscriptionDate(Date inscriptionDate) {
		this.inscriptionDate = inscriptionDate;
	}

	@Iri("http://schemas.talis.com/2005/user/schema#lastLoginTime")
	public Date getLastLoginTime() {
		return lastLoginTime;
	}
	
	@Iri("http://schemas.talis.com/2005/user/schema#lastLoginTime")
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	@Iri("http://schemas.talis.com/2005/user/schema#loginName")
	public String getLoginName() {
		return login;
	}
	
	@Iri("http://schemas.talis.com/2005/user/schema#loginName")
	public void setLoginName(String loginName) {
		this.login = loginName;
	}
	
	@Iri("http://schemas.talis.com/2005/user/schema#mail")
	public String getMail() {
		return mail;
	}
	
	@Iri("http://schemas.talis.com/2005/user/schema#mail")
	public void setMail(String mail) {
		this.mail = mail;
	}

	@Iri("http://schemas.talis.com/2005/user/schema#passwordSha1Hash")
	public String getPasswordSha1Hash() {
		return password;
	}
	
	@Iri("http://schemas.talis.com/2005/user/schema#passwordSha1Hash")
	public void setPasswordSha1Hash(String passwordSha1Hash) {
		this.password = passwordSha1Hash;
	}
	
	@Sparql("INSERT { $user rdf:description $this}")
	public void addUser(@Bind("user") String user) {}
}
