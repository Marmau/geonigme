package controllers;

import play.Play;
import play.mvc.Controller;
import play.mvc.Result;

public class RdfController extends Controller {

	public static Result ontology() {
		response().setContentType("text/turtle");

		return ok(Play.application().resourceAsStream("public/rdf/ontology.ttl"));
	}
}
