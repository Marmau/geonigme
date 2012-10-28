package controllers;

import java.util.List;

import org.openrdf.query.QueryEvaluationException;
import org.openrdf.repository.RepositoryException;

import play.mvc.*;

public class Manager extends Controller {

	public static Result dashboard() throws RepositoryException, QueryEvaluationException {
		if (!User.isLogged()) {
			return redirect(routes.Application.index());
		}
		
		models.User user = User.getLoggedUser();
		List<models.Hunt> hunts = user.getHunts();

		return ok(views.html.dashboard.dashboard.render(hunts));

	}

}
