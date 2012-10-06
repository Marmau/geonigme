package models.old;

import org.openrdf.annotations.Iri;

@Iri(User.NS + "User")
public class User {
	public static final String NS = "http://test.com/test#";

	@Iri(NS + "login")
	private String login;
	
	@Iri(NS + "mdp")
	private String mdp;
	
	@Iri(NS + "mode")
	private String mode;
	
	@Iri(NS + "mail")
	private String mail;

	public User() {

	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	
	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
	
	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String toString() {
		return login;
	}
}
