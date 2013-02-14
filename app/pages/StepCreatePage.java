package pages;

import global.PageLink;
import global.PageMenuItem;
import models.Hunt;
import controllers.routes;

public class StepCreatePage extends DashboardPage {

	public static final String commonName = "stepcreate";

	public StepCreatePage(String title, String startJS) throws Exception {
		super("stepcreate", title, null, startJS);
		menu.setCSSClasses("breadcrumb");
		menu.add("dashboard");
		System.out.println("StepCreatePage Adding to menu "+HuntShowPage.commonName);
		menu.add(HuntShowPage.commonName);
		menu.add(getName());
	}

	// The items of the menu could (should) be a copy
	public void setMenuParameters(Hunt hunt) {
		PageMenuItem pmi;
		
		// This create page
		pmi = menu.getPage(getName());
		fillLink(pmi, hunt);

		// The hunt show page
		pmi = menu.getPage(HuntShowPage.commonName);
		HuntShowPage.fillLink(pmi, hunt);
	}

	public static void fillLink(PageLink link, Hunt hunt) {
		link.setRoute(routes.StepController.create(hunt.getId()));
	}
}
