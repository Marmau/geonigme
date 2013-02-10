package pages;

import global.Page;
import models.Hunt;
import controllers.routes;
import play.api.mvc.Call;

public class HuntEditPage extends DashboardPage {

	protected Hunt hunt = null;

	public HuntEditPage(String title, String startJS) throws Exception {
		super("huntedit", title, null, startJS);
		menu.setCSSClasses("breadcrumb");
		menu.add("dashboard");
		menu.add(new HuntShowPage((HuntShowPage) Page.get("huntshow")));// Copy
																		// it
		menu.add(new HuntEditPage(this));// Copy it
	}

	public HuntEditPage(HuntEditPage other) throws Exception {
		super(other);
	}

	public void setMyParameters(Hunt hunt) {
		this.hunt = hunt;
	}

	// The items of the menu could (should) be a copy
	public void setMenuParameters(Hunt hunt) {
		// The show page
		HuntShowPage p2 = (HuntShowPage) menu.getPage("huntshow");// Clone
		p2.setLabel(hunt.getLabel());
		p2.setMyParameters(hunt);

		// This edit page
		HuntEditPage p3 = (HuntEditPage) menu.getPage(name);// Clone
		p3.setMyParameters(hunt);
	}

	@Override
	public Call getRoute() {
		return routes.HuntController.update(hunt.getId());
	}
}
