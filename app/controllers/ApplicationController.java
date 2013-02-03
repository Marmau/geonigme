package controllers;

import play.mvc.*;

public class ApplicationController extends Controller {

	public static Result index() {
		return ok(views.html.global.index.render());
	}

	public static Result submitContactForm() {
		return TODO;
	}
}