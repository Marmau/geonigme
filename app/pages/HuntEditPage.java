package pages;

import global.PageLink;
import global.PageMenuItem;
import models.Hunt;
import controllers.routes;

public class HuntEditPage extends DashboardPage {

	public static final String commonName = "huntedit";

	public HuntEditPage(String title, String startJS) throws Exception {
		super(commonName, title, null, startJS);
		menu.setCSSClasses("breadcrumb");
		menu.add("dashboard");
		System.out.println("HuntEditPage Adding to menu "+HuntShowPage.commonName);
		menu.add(HuntShowPage.commonName);
		menu.add(getName());
	}

	// The items of the menu could (should) be a copy
	public void setMenuParameters(Hunt hunt) {
		PageMenuItem pmi;
		
		// This edit page
		pmi = menu.getPage(getName());
		fillLink(pmi, hunt);
		
		// The show page
		pmi = menu.getPage(HuntShowPage.commonName);
		HuntShowPage.fillLink(pmi, hunt);

	}

	public static void fillLink(PageLink link, Hunt hunt) {
		link.setRoute(routes.HuntController.update(hunt.getId()));
		//link.setLabel(hunt.getLabel());
	}
}
