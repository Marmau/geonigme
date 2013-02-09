package pages;

import global.Page;
import models.Right;
import play.api.mvc.Call;

public class GamePage extends Page {

	public GamePage(String name, String title, Call route, String startJS) throws Exception {
		super(name, title, route, Right.NONE, startJS);
		this.bodyClasses = "game";
	}

	public GamePage(GamePage other) throws Exception {
		super(other);
	}

	protected void setup() {
		this.bodyClasses = "game";
	}
}
