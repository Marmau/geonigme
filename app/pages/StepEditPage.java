package pages;

import global.PageLink;
import global.PageMenuItem;
import models.Step;
import controllers.routes;
import play.i18n.Messages;

public class StepEditPage extends DashboardPage {

	public static final String commonName = "stepedit";
	
	public StepEditPage(String title, String startJS) throws Exception {
		super(commonName, title, null, startJS);
		menu.setCSSClasses("breadcrumb");
		menu.add("dashboard");
		menu.add(HuntShowPage.commonName);
		menu.add(commonName);
	}

	// The items of the menu could (should) be a copy
	public void setMenuParameters(Step step) {
		PageMenuItem pmi;
		
		// This edit page
		pmi = menu.getPage(getName());
		fillLink(pmi, step);

		// The hunt show page
		pmi = menu.getPage(HuntShowPage.commonName);
		HuntShowPage.fillLink(pmi, step.getHunt());
	}

	public static void fillLink(PageLink link, Step step) {
		link.setRoute(routes.StepController.update(step.getId()));
		link.setLabel(Messages.get("pages.stepedit", step.getNumber()));
	}
}
