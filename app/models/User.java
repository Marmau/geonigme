package models;

import java.util.Date;
import java.util.List;

import org.openrdf.annotations.Iri;
import org.openrdf.annotations.Sparql;

@Iri("http://schemas.talis.com/2005/user/schema#User")
public class User {

	public static final String URI = "http://geonigme.fr/user/";

	private Date inscriptionDate;
	private Date lastLoginTime;
	private String login;
	private String mail;
	private String password;

	@Sparql(NS.PREFIX +
		"SELECT ?hunt { ?hunt gngm:createdBy $this. ?hunt gngm:modifiedAt ?date } ORDER BY ?date")
	public List<Hunt> getHunts() {
		return null;
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

}
