package controllers;

import global.AssociatedPage;

import java.util.List;

import org.openrdf.query.QueryEvaluationException;
import org.openrdf.repository.RepositoryException;

import play.mvc.*;
import repository.UserRepository;

public class ManagerController extends Controller {

	@AssociatedPage("dashboard")
	public static Result dashboard() throws RepositoryException, QueryEvaluationException {
		if (!UserRepository.isLogged()) {
			return redirect(routes.ApplicationController.index());
		}
		
		models.User user = UserRepository.getLoggedUser();
		List<models.Hunt> hunts = user.getHunts();

		return ok(views.html.dashboard.dashboard.render(hunts));
	}

}
