package controllers;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;

import play.data.Form;
import play.mvc.*;

import models.old.*;

public class User extends Controller {
	
	static String pattern = "([^A-Za-z0-9]+)";

	public static Result submitLoginForm() {
		Form<models.old.User> userForm = form(models.old.User.class);

		models.old.User userResponse = userForm.bindFromRequest().get();
		String mdp = userResponse.getMdp();
		String login = userResponse.getLogin();

		// Le modele user est à remplir avec les fonctions du userrepository (entre autre), il est utilisé pour le moment
		models.User userRepo = new models.User();
		//models.User user  = userRepo.findOneBy(array("pseudo" => login));
		
		
	      //Vérifications
	      String errors = "";

	      if (userRepo == null || userRepo.getPasswordSha1Hash() != mdp)
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
	    	  userRepo.setLastLoginTime(new Date());
	    	  session("user", userRepo.getLoginName()); // id ?
	        
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
	    
	    models.User userRepo = new models.User();


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
	        return ok(views.html.play-login);
	      }
	      else {
	    	  userRepo.setMail(mail);
	    	  userRepo.setLoginName(login);
	    	  userRepo.setPasswordSha1Hash(mdp);
	    	  userRepo.setInscriptionDate(new Date());
	    	  userRepo.setLastLoginTime(new Date());
	    	  
	    	  // gestion de la persistence
	    	  // TODO

	        session("user", userRepo.getLoginName()); // id ?
	        
	        if (userResponse.getMode() == "mobile") 
	        	return ok();//views.html.play-selection);
	        else
	        	return ok();//views.html.manager-register);
	      }
	}
}
