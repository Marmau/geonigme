package repository;

import global.Sesame;

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
}
