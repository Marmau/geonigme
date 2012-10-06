package controllers;

import java.util.Date;

import play.data.Form;
import play.mvc.*;

import models.old.*;
import repository.UserRepository;

public class User extends Controller {
	
	static String pattern = "([^A-Za-z0-9]+)";

	public static Result submitLoginForm() {
		Form<models.old.User> userForm = form(models.old.User.class);

		models.old.User userResponse = userForm.bindFromRequest().get();
		String mdp = userResponse.getMdp();
		String login = userResponse.getLogin();

		// Le modele user est à remplir avec les fonctions du userrepository (entre autre), il est utilisé pour le moment
		UserRepository userRepo = new UserRepository();
		//models.User user  = userRepo.findOneBy(array("pseudo" => login));
		
		
	      //Vérifications
	      String errors = "";

	      if (userRepo == null || userRepo.getMdp() != mdp)
	        errors += "<div id=\"error-pseudo\">Le pseudo et/ou le mot de passe sont incorrects.</div>";

	      if (errors.isEmpty()) {
	    	  flash("errors", errors);
	    	  flash("mode", userResponse.getMode());
	    	  
	    	  if (userResponse.getMode() == "mobile") 
	    		  return ok();//views.html.play-login);
	    	  else
	    		  return ok();//views.html.manager-login);
	      }
	      else {
	    	  userRepo.setLastConnection(new Date());
	    	  session("user", userRepo.getLogin()); // id ?
	        
	          if (userResponse.getMode() == "mobile") 
	    		  return ok();//views.html.play-selection);
	    	  else
	    		  return ok();//views.html.manager-login);
	      }
		
	}

	public static Result logout() {
		session().clear();
		return ok(views.html.global.index.render());
	}

	public static Result submitRegisterForm() {
		Form<models.old.User> userForm = form(models.old.User.class);
		models.old.User userResponse = userForm.bindFromRequest().get();
		
		String mdp = userResponse.getMdp();
		String login = userResponse.getLogin();
		String mail = userResponse.getMail();

	    //Vérifications
	    String errors = "";
	    
	    UserRepository userRepo = new UserRepository();


	      if (login.length() < 4)
	        errors += "<div id=\"error-pseudo\">Le pseudo doit contenir un mininum de 4 caractères.</div>";
	      else if (login.matches(pattern))
	        errors += "<div id=\"error-pseudo\">Le pseudo contient des caractères invalides: caractères supportés : A->Z, a->z, 0->9.</div>";
//	      else if(userRepo.findOneBy(array("pseudo" => $pseudo)))
//	        errors += "<div id=\"error-pseudo\">Ce pseudo existe déjà.</div>";
	      if (mdp.length() < 4)
	        errors += "<div id=\"error-pass\">Le mot de passe doit contenir un mininum de 4 caractères.</div>";
//	      else if (mdp.isn t valid)
//	        errors += "<div id=\"error-pass\">Le mot de passe contient des caractères invalides: caractères supportés : A->Z, a->z, 0->9, À->ÿ, [espace], -.</div>";
	     
	      if (mail.length() == 0)
	        errors += "<div id=\"error-mail\">L\'e-mail est un champ obligatoire.</div>";
//	      else if (mail.isn t valid)
//	        errors += "<div id=\"error-mail\">L\'e-mail n\'est pas spécifié dans un format valide.</div>";
	     
	      if (errors.isEmpty()) {
	        flash("errors", errors);
	        flash("mode", userRepo.getMode());
	        if (userRepo.getMode() == "mobile")
	          return ok();//views.html.play-login);
	        else
	          return ok();//views.html.manager-login);
	      }
	      else {
	    	  userRepo.setMail(mail);
	    	  userRepo.setLogin(login);
	    	  userRepo.setMdp(mdp);
	    	  userRepo.setInscriptionDate(new Date());
	    	  userRepo.setLastConnection(new Date());
	    	  
	    	  // gestion de la persistence
	    	  // TODO

	        session("user", userRepo.getLogin()); // id ?
	        
	        if (userResponse.getMode() == "mobile") 
	        	return ok();//views.html.play-selection);
	        else
	        	return ok();//views.html.manager-register);
	      }
	}
}
