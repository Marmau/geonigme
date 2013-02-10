package pages;

import models.Hunt;
import controllers.routes;
import play.api.mvc.Call;

public class HuntShowPage extends DashboardPage {

	protected Hunt hunt = null;

	public HuntShowPage(String title, String startJS) throws Exception {
		super("huntshow", title, null, startJS);
		menu.setCSSClasses("breadcrumb");
		menu.add("dashboard");
		menu.add(new HuntShowPage(this));// Copy it
	}

	public HuntShowPage(HuntShowPage other) throws Exception {
		super(other);
	}

	public void setMyParameters(Hunt hunt) {
		this.hunt = hunt;
	}

	// The items of the menu could (should) be a copy
	public void setMenuParameters(Hunt hunt) {
		HuntShowPage p = (HuntShowPage) menu.getPage(name);// Clone
		p.setMyParameters(hunt);
		p.setLabel(hunt.getLabel() + " |<span class=\"small\">" + hunt.getStringLevel() + "</span>");
	}

	@Override
	public Call getRoute() {
		return routes.HuntController.show(hunt.getId());
	}
}
