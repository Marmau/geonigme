package pages;

import global.Page;
import models.Right;
import play.api.mvc.Call;

public class GamePage extends Page {

	public GamePage(String name, String title, Call route, Right accessRight, String startJS) throws Exception {
		super(name, title, route, accessRight, startJS);
		this.bodyClasses = "game";
	}
}
