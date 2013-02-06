package pages;

import global.Page;
import models.Right;
import play.api.mvc.Call;

public class HomePage extends Page {

	public HomePage(String name, String title, Call route, String startJS) throws Exception {
		super(name, title, route, Right.NONE, startJS);
	}
	
	public HomePage(HomePage other) throws Exception {
		super(other);
	}
	
	protected void setup() {
		this.bodyClasses = "home";
	}
}
