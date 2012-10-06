package repository;

import java.util.Date;

import models.old.User;

public class UserRepository extends User {

	private int uid;
	private String mail;
	private String pseudo;
	private String password;
	private Date inscriptionDate;
	private Date lastConnection;
	private Date birthday;
	private String profession;
	private int type;

	public UserRepository() {
		type = 1;
	}

	public int getUid() {
		return uid;
	}
	  
	public User setUid(int uid) {
		this.uid = uid;
	    return this;
	}

	public User setPseudo(String pseudo) {
	    this.pseudo = pseudo;
	    return this;
	}

	public String getPseudo() {
	    return this.pseudo;
	}

	public User setPassword(String pwd) {
		this.password = pwd;
	    return this;
	}

	public String getPassword() {
	    return this.password;
	}

	public User setInscriptionDate(Date inscriptionDate) {
	    this.inscriptionDate = inscriptionDate;
	    return this;
	}

	public Date getInscriptionDate() {
	    return this.inscriptionDate;
	}

	public User setLastConnection(Date lastConnection) {
	    this.lastConnection = lastConnection;
	    return this;
	}

	public Date getLastConnection() {
	    return this.lastConnection;
	}

	public User setBirthday(Date birthday) {
	    this.birthday = birthday;
	    return this;
	}

	public Date getBirthday() {
	    return this.birthday;
	}

	public User setProfession(String profession) {
		this.profession = profession;
	    return this;
	}

	public String getProfession() {
	    return this.profession;
	}

	public User setType(int type) {
	    this.type = type;
	    return this;
	}

	public int getType() {
	    return this.type;
	 }
}
