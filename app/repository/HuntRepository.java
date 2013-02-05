package repository;

import global.Sesame;

import java.util.ArrayList;
import java.util.List;

import models.Hunt;
import models.NS;

import org.openrdf.query.MalformedQueryException;
import org.openrdf.query.QueryEvaluationException;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.object.ObjectConnection;
import org.openrdf.repository.object.ObjectQuery;

public class HuntRepository {
	
	public static List<Hunt> getHuntsSortedByDate() throws QueryEvaluationException, MalformedQueryException, RepositoryException {
		ObjectConnection oc = Sesame.getObjectConnection();
		ObjectQuery query = oc.prepareObjectQuery(NS.PREFIX
				+ "SELECT ?hunt WHERE { ?hunt gngm:modifiedAt ?date } ORDER BY DESC(?date) LIMIT 20");

		return query.evaluate(Hunt.class).asList();
	}
	
	public static Hunt get(String uid) {
		ObjectConnection oc = Sesame.getObjectConnection();
		try {
			return oc.getObject(models.Hunt.class, models.Hunt.URI + uid);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static List<Hunt> getAll(String orderBy) {
		List<Hunt> hunts = new ArrayList<Hunt>();
		try {
			ObjectConnection oc = Sesame.getObjectConnection();
			String sqlQuery = "SELECT ?hunt WHERE { ?hunt gngm:"+orderBy+" ?orderBy } ORDER BY DESC(?orderBy)";
			ObjectQuery query = oc.prepareObjectQuery(NS.PREFIX + 
				sqlQuery);
			hunts = query.evaluate(models.Hunt.class).asList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hunts;
	}
	
	public static List<Hunt> getAll() {
		return getAll("modifiedAt");
	}
}
