package pages;

import play.i18n.Messages;
import models.Step;
import global.PageLink;
import global.PageMenuItem;
import controllers.routes;

public class EnigmaCreatePage extends DashboardPage {

	public static final String commonName = "enigmacreate";

	public EnigmaCreatePage(String title, String startJS) throws Exception {
		super(commonName, title, null, startJS);
		menu.setCSSClasses("breadcrumb");
		menu.add("dashboard");
		menu.add("huntshow");
		menu.add("stepedit");
		menu.add(getName());
	}

	public void setMenuParameters(Step step) {
		PageMenuItem pmi;
		
		// This edit page
		pmi = menu.getPage(getName());
		fillLink(pmi, step);

		// The step edit page
		pmi = menu.getPage(StepEditPage.commonName);
		StepEditPage.fillLink(pmi, step);
		pmi.setLabel(Messages.get("pages.stepNumbered", step.getNumber()));

		// The hunt show page
		pmi = menu.getPage(HuntShowPage.commonName);
		HuntShowPage.fillLink(pmi, step.getHunt());
	}

	public static void fillLink(PageLink link, Step step) {
		link.setRoute(routes.EnigmaController.create(step.getId()));
	}
}
