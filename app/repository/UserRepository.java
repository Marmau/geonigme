package repository;

import global.Sesame;

import java.util.ArrayList;
import java.util.List;

import models.NS;
import models.User;

import org.openrdf.repository.object.ObjectConnection;
import org.openrdf.repository.object.ObjectQuery;

import play.mvc.Http.Context;

public class UserRepository {
	static String USER_SESSION = "user";

	public static boolean isLogged() {
		return getLoggedUser() != null;
	}

	public static models.User getLoggedUser() {
		ObjectConnection oc = Sesame.getObjectConnection();
		String uid = Context.current().session().get(USER_SESSION);
		if (uid == null) {
			return null;
		}

		try {
			return oc.getObject(models.User.class, models.User.URI + uid);
		} catch (Exception e) {
			return null;
		}
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
			// users = oc.getObjects(models.User.class).asList();

			String sqlQuery = "SELECT ?user WHERE { ?user user:" + orderBy + " ?orderBy } ORDER BY ASC(?orderBy)";
			// System.out.println(sqlQuery);
			ObjectQuery query = oc.prepareObjectQuery(NS.PREFIX + sqlQuery);
			users = query.evaluate(models.User.class).asList();
			// System.out.println("We got "+users.size()+" users");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}

	public static List<User> getAll() {
		return getAll("loginName");
	}
}
