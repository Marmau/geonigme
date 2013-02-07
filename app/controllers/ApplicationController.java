package controllers;

import global.AssociatedPage;
import global.Global;
import play.mvc.*;

public class ApplicationController extends Controller {
	
	@AssociatedPage("home")
	public static Result index() {
		return ok(views.html.global.index.render());
	}

	public static Result appLoad() {
		//Global.displayAppLoad();
		//return ok("Done.");
		return ok(Global.displayAppLoad());
	}

	public static Result submitContactForm() {
		return TODO;
	}
}