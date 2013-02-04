package repository;

import global.Sesame;

import org.openrdf.repository.object.ObjectConnection;

import play.mvc.Http;

public class UserRepository {
	static String USER_SESSION = "user";
	
	public static boolean isLogged() {
		return getLoggedUser() != null;
	}

	public static models.User getLoggedUser() {
		ObjectConnection oc = Sesame.getObjectConnection();
		String uid = Http.Context.current().session().get(USER_SESSION);
		if (uid == null) {
			return null;
		}
		
		try {
			return oc.getObject(models.User.class, models.User.URI + uid);
		} catch (Exception e) {
			return null;
		}
	}
}
