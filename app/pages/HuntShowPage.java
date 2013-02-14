package pages;

import global.PageLink;
import global.PageMenuItem;
import models.Hunt;
import controllers.routes;

public class HuntShowPage extends DashboardPage {

	public static final String commonName = "huntshow";

	public HuntShowPage(String title, String startJS) throws Exception {
		super(commonName, title, null, startJS);
		menu.setCSSClasses("breadcrumb");
		menu.add("dashboard");
		menu.add(getName());
	}

	// The items of the menu could (should) be a copy
	public void setMenuParameters(Hunt hunt) {
		PageMenuItem pmi;
		
		// This show page.
		pmi = menu.getPage(getName());
		fillLink(pmi, hunt);
		pmi.setLabel(hunt.getLabel()+" |<span class=\"small\">"+hunt.getStringLevel()+"</span>");
	}

	public static void fillLink(PageLink link, Hunt hunt) {
		link.setRoute(routes.HuntController.show(hunt.getId()));
		link.setLabel(hunt.getLabel());
	}
}
