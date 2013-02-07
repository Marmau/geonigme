package pages;

import global.Page;
import models.Right;
import play.api.mvc.Call;

public class DashboardPage extends Page {

	public DashboardPage(String name, String title, Call route, String startJS) throws Exception {
		super(name, title, route, Right.MEMBER_AREA, startJS);
	}
	
	public DashboardPage(DashboardPage other) throws Exception {
		super(other);
	}
	
	protected void setup() {
		this.bodyClasses = "dashboard";
	}
}
