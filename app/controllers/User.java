package controllers;

import global.Sesame;

import java.util.Date;
import java.util.UUID;

import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.object.ObjectConnection;

import play.data.Form;
import play.mvc.*;

public class User extends Controller {

	static String pattern = "([^A-Za-z0-9]+)";

	public static Result submitLoginForm() {
		Form<models.User> userForm = form(models.User.class);

		models.User userResponse = userForm.bindFromRequest().get();
		String mdp = userResponse.getPasswordSha1Hash();
		String login = userResponse.getLoginName();

		// Le modele user est à remplir avec les fonctions du userrepository
		// (entre autre), il est utilisé pour le moment
		models.User userRepo = new models.User();
		// models.User user = userRepo.findOneBy(array("pseudo" => login));

		// Vérifications
		String errors = "";

		if (userRepo == null || userRepo.getPasswordSha1Hash() != mdp)
			errors += "<div id=\"error-pseudo\">Le pseudo et/ou le mot de passe sont incorrects.</div>";

		if (!errors.isEmpty()) {
			flash("errors", errors);
			return ok(views.html.global.index.render());
		} else {
			userRepo.setLastLoginTime(new Date());
			session("user", userRepo.getLoginName()); // id ?
			return ok(views.html.dashboard.mainDashboard.render("test", "test", null, null));
		}

	}

	public static Result logout() {
		session().clear();
		return ok(views.html.global.index.render());
	}

	public static Result submitRegisterForm() throws RepositoryException {
		models.User userResponse = form(models.User.class).bindFromRequest().get();
		System.out.println("test");
		System.out.println(userResponse.getLoginName());
		System.out.println(userResponse.getMail());

		String mdp = "";
		mdp = userResponse.getPasswordSha1Hash();
		String login = "";
		login = userResponse.getLoginName();
		String mail = "";
		mail = userResponse.getMail();

		// Vérifications
		String errors = "";

		models.User userRepo = new models.User();
		if (login.length() < 4)
			errors += "<div id=\"error-pseudo\">Le pseudo doit contenir un mininum de 4 caractères.</div>";
		else if (login.matches(pattern))
			errors += "<div id=\"error-pseudo\">Le pseudo contient des caractères invalides: caractères supportés : A->Z, a->z, 0->9.</div>";
		// else if(userRepo.findOneBy(array("pseudo" => $pseudo)))
		// errors += "<div id=\"error-pseudo\">Ce pseudo existe déjà.</div>";
		if (mdp.length() < 4)
			errors += "<div id=\"error-pass\">Le mot de passe doit contenir un mininum de 4 caractères.</div>";
		// else if (mdp.isn t valid)
		// errors +=
		// "<div id=\"error-pass\">Le mot de passe contient des caractères invalides: caractères supportés : A->Z, a->z, 0->9, À->ÿ, [espace], -.</div>";

		if (mail.length() == 0)
			errors += "<div id=\"error-mail\">L\'e-mail est un champ obligatoire.</div>";
		// else if (mail.isn t valid)
		// errors +=
		// "<div id=\"error-mail\">L\'e-mail n\'est pas spécifié dans un format valide.</div>";

		if (!errors.isEmpty()) {
			flash("errors", errors);
			return ok(views.html.global.index.render());
		} else {
			userRepo.setMail(mail);
			userRepo.setLoginName(login);
			userRepo.setPasswordSha1Hash(mdp);
			userRepo.setInscriptionDate(new Date());
			userRepo.setLastLoginTime(new Date());

			// gestion de la persistence
			// v2
			ObjectConnection oc = Sesame.getObjectConnection();
			oc.addObject(models.User.URI + UUID.randomUUID().toString(), userRepo);

			session("user", userRepo.getLoginName()); // id ?
			return ok(views.html.dashboard.mainDashboard.render("test", "test", null, null));
		}
	}
}
