package pages;

import global.Page;
import models.Right;
import play.api.mvc.Call;

public class AdminPanelPage extends Page {

	public AdminPanelPage(String name, String title, Call route, Right accessRight, String startJS) throws Exception {
		super(name, title, route, accessRight, startJS);
	}

	public AdminPanelPage(AdminPanelPage other) throws Exception {
		super(other);
	}

	protected void setup() {
		this.bodyClasses = "adminpanel";
	}
}
