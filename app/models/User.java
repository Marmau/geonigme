package models;

import org.openrdf.annotations.Iri;

@Iri(User.NS + "User")
public class User {
    public static final String NS = "http://test.com/test#";

    @Iri(NS + "login")
    private String login;

    public User() {

    }

    public String getLogin() {
	return login;
    }

    public void setLogin(String login) {
	this.login = login;
    }

    public String toString() {
	return login;
    }
}
