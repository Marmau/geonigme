package controllers;

import java.util.List;

import org.openrdf.query.QueryEvaluationException;
import org.openrdf.repository.RepositoryException;

import play.mvc.*;

public class ManagerController extends Controller {

	public static Result dashboard() throws RepositoryException, QueryEvaluationException {
		if (!UserController.isLogged()) {
			return redirect(routes.ApplicationController.index());
		}
		
		models.User user = UserController.getLoggedUser();
		List<models.Hunt> hunts = user.getHunts();

		return ok(views.html.dashboard.dashboard.render(hunts));
	}

}
