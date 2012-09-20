package controllers;

import com.hp.hpl.jena.datatypes.xsd.XSDDatatype;
import com.hp.hpl.jena.rdf.model.*;

import play.*;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {

	public static Result index() {
		Model m = ModelFactory.createDefaultModel();
		String NS = "http://exemple.com/";
		
		Resource r = m.createResource(NS + "r");
		Property p = m.createProperty(NS + "p");
		
		r.addProperty(p, "hello world", XSDDatatype.XSDstring);
		
		m.write(System.out, "Turtle");
		
		return ok(index.render("Your new application is ready."));
	}

}