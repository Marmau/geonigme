package pages;

import models.Hunt;
import controllers.routes;
import play.api.mvc.Call;

public class HuntPlayPage extends GamePage {
	
	protected Hunt hunt = null;

	public HuntPlayPage(String name, String title, String startJS) throws Exception {
		super(name, title, null, startJS);
		menu.setCSSClasses("breadcrumb");
		menu.add("huntlist");
		menu.add(new HuntPlayPage(this));//Copy it
	}
	
	public HuntPlayPage(String title, String startJS) throws Exception {
		this("huntplay", title, startJS);
	}
	
	public HuntPlayPage(HuntPlayPage other) throws Exception {
		super(other);
	}
	
	public void setMyParameters(Hunt hunt) {
		this.hunt = hunt;
	}

	// The items of the menu could (should) be a copy
	public void setMenuParameters(Hunt hunt) {
		// This play page
		HuntPlayPage p2 = (HuntPlayPage) menu.getPage(name);// Should be a copy
		p2.setMyParameters(hunt);
		p2.setTitle(hunt.getLabel());
	}
	
	@Override
	public Call getRoute() {
		return routes.GameController.playHunt(hunt.getId());
	}
}
