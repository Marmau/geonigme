package pages;

import global.PageLink;
import global.PageMenuItem;
import models.Hunt;
import controllers.routes;

public class HuntPlayPage extends GamePage {

	public static final String commonName = "huntplay";

	public HuntPlayPage(String name, String title, String startJS) throws Exception {
		super(name, title, null, startJS);
		menu.setCSSClasses("breadcrumb");
		menu.add("huntlist");
		menu.add(getName());
	}

	public HuntPlayPage(String title, String startJS) throws Exception {
		this(commonName, title, startJS);
	}

	// The items of the menu could (should) be a copy
	public void setMenuParameters(Hunt hunt) {
		PageMenuItem pmi;
		
		// This play page
		pmi = menu.getPage(getName());
		fillLink(pmi, hunt);
	}

	public static void fillLink(PageLink link, Hunt hunt) {
		link.setRoute(routes.GameController.playHunt(hunt.getId()));
		link.setLabel(hunt.getLabel());
	}
}
