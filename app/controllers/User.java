package controllers;

import java.io.File;
import java.util.Date;

import org.openrdf.model.URI;
import org.openrdf.model.ValueFactory;
import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.config.RepositoryConfigException;
import org.openrdf.repository.object.ObjectConnection;
import org.openrdf.repository.object.ObjectRepository;
import org.openrdf.repository.object.config.ObjectRepositoryFactory;
import org.openrdf.repository.sail.SailRepository;
import org.openrdf.sail.inferencer.fc.ForwardChainingRDFSInferencer;
import org.openrdf.sail.nativerdf.NativeStore;
import org.w3c.dom.Document;

import com.sun.org.apache.xerces.internal.dom.DocumentImpl;

import play.data.Form;
import play.mvc.*;

public class User extends Controller {
	
	static String pattern = "([^A-Za-z0-9]+)";

	public static Result submitLoginForm() {
		Form<models.User> userForm = form(models.User.class);

		models.User userResponse = userForm.bindFromRequest().get();
		String mdp = userResponse.getPasswordSha1Hash();
		String login = userResponse.getLoginName();

		// Le modele user est à remplir avec les fonctions du userrepository (entre autre), il est utilisé pour le moment
		models.User userRepo = new models.User();
		//models.User user  = userRepo.findOneBy(array("pseudo" => login));
		
		
	      //Vérifications
	      String errors = "";

	      if (userRepo == null || userRepo.getPasswordSha1Hash() != mdp)
	        errors += "<div id=\"error-pseudo\">Le pseudo et/ou le mot de passe sont incorrects.</div>";

	      if (!errors.isEmpty()) {
	    	  flash("errors", errors);
	    	  return ok(views.html.global.index.render());
	      }
	      else {
	    	  userRepo.setLastLoginTime(new Date());
	    	  session("user", userRepo.getLoginName()); // id ?
	    	  return ok(views.html.dashboard.mainDashboard.render("test","test", null, null));
	      }
		
	}

	public static Result logout() {
		session().clear();
		return ok(views.html.global.index.render());
	}

	public static Result submitRegisterForm() {
		Form<models.User> userForm = form(models.User.class);
		models.User userResponse = userForm.bindFromRequest().get();
		System.out.println("test");
		System.out.println(userResponse.getLoginName());
		System.out.println(userResponse.getMail());
		
		String mdp = "";
		mdp = userResponse.getPasswordSha1Hash();
		String login = "";
		login = userResponse.getLoginName();
		String mail = "";
		mail = userResponse.getMail();

	    //Vérifications
	    String errors = "";
	    
	    models.User userRepo = new models.User();
//	    if (login.length() < 4)
//	    	errors += "<div id=\"error-pseudo\">Le pseudo doit contenir un mininum de 4 caractères.</div>";
//	    else if (login.matches(pattern))
//	    	errors += "<div id=\"error-pseudo\">Le pseudo contient des caractères invalides: caractères supportés : A->Z, a->z, 0->9.</div>";
//		//	      else if(userRepo.findOneBy(array("pseudo" => $pseudo)))
//		//	        errors += "<div id=\"error-pseudo\">Ce pseudo existe déjà.</div>";
//	    if (mdp.length() < 4)
//	    	errors += "<div id=\"error-pass\">Le mot de passe doit contenir un mininum de 4 caractères.</div>";
//		//	      else if (mdp.isn t valid)
//		//	        errors += "<div id=\"error-pass\">Le mot de passe contient des caractères invalides: caractères supportés : A->Z, a->z, 0->9, À->ÿ, [espace], -.</div>";
//		 
//	    if (mail.length() == 0)
//	    	errors += "<div id=\"error-mail\">L\'e-mail est un champ obligatoire.</div>";
//		//	      else if (mail.isn t valid)
//		//	        errors += "<div id=\"error-mail\">L\'e-mail n\'est pas spécifié dans un format valide.</div>";
//		 
	    if (!errors.isEmpty()) {
	    	flash("errors", errors);
	    	return ok(views.html.global.index.render());
	    }
	    else {
	    	userRepo.setMail(mail);
	    	userRepo.setLoginName(login);
	    	userRepo.setPasswordSha1Hash(mdp);
	    	userRepo.setInscriptionDate(new Date());
	    	userRepo.setLastLoginTime(new Date());
			  
			// gestion de la persistence
	    	// v1
//	    	try {
//	    		String dir = "/store/geonigme";
//	    		Repository sesame = new org.openrdf.repository.sparql.SPARQLRepository(dir);
//	    		ObjectRepositoryFactory factory = new ObjectRepositoryFactory();
//	    		ObjectRepository repository = factory.createRepository(sesame);
//	    		ObjectConnection con = repository.getConnection();
//	    		ValueFactory vf = con.getValueFactory();
//	    		URI id = vf.createURI("http://geonigme.fr/rdf/ontology#user/" + userRepo.getLoginName());
//	    		con.addObject(id, userRepo);
//	    	} catch (Exception e) {
//	    		e.printStackTrace();
//	    	}
	    	
	    	// v2
	    	userRepo.addUser(login);
		
		    session("user", userRepo.getLoginName()); // id ?
		    return ok(views.html.dashboard.mainDashboard.render("test", "test", null, null));
		  }
	}
}
