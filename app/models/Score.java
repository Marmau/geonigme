package models;

import org.openrdf.annotations.Iri;

@Iri(NS.GNGM + "Score")
public class Score {

	public static final String URI = "http://geonigme.fr/score/";

	private Hunt hunt;
	private User user;
	private Integer value;

	@Iri(NS.GNGM + "scoreOfHunt")
	public Hunt getHunt() {
		return hunt;
	}

	@Iri(NS.GNGM + "scoreOfHunt")
	public void setHunt(Hunt hunt) {
		this.hunt = hunt;
	}

	@Iri(NS.GNGM + "scoreOfUser")
	public User getUser() {
		return user;
	}

	@Iri(NS.GNGM + "scoreOfUser")
	public void setUSer(User user) {
		this.user = user;
	}

	@Iri(NS.GNGM + "value")
	public Integer getValue() {
		return value;
	}

	@Iri(NS.GNGM + "value")
	public void setValue(Integer value) {
		this.value = value;
	}
}
