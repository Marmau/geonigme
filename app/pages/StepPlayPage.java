package pages;

import global.PageLink;
import global.PageMenuItem;
import models.Step;
import controllers.routes;
import play.i18n.Messages;

public class StepPlayPage extends GamePage {

	public static final String commonName = "stepplay";

	public StepPlayPage(String title, String startJS) throws Exception {
		super(commonName, title, null, startJS);
		menu.setCSSClasses("breadcrumb");
		menu.add("huntlist");
		menu.add(HuntPlayPage.commonName);
		menu.add(getName());
	}

	// The items of the menu could (should) be a copy
	public void setMenuParameters(Step step) {
		PageMenuItem pmi;
		
		// This play page
		pmi = menu.getPage(getName());
		fillLink(pmi, step);

		// The hunt play page
		pmi = menu.getPage(HuntPlayPage.commonName);
		HuntPlayPage.fillLink(pmi, step.getHunt());
	}

	public static void fillLink(PageLink link, Step step) {
		link.setRoute(routes.GameController.go());
		link.setLabel(Messages.get("pages.stepNumbered", step.getNumber()));
	}
}
