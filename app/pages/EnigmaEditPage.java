package pages;

import models.Enigma;
import models.Step;
import global.PageLink;
import global.PageMenuItem;
import controllers.routes;
import play.i18n.Messages;

public class EnigmaEditPage extends DashboardPage {

	public static final String commonName = "enigmaedit";

	public EnigmaEditPage(String title, String startJS) throws Exception {
		super(commonName, title, null, startJS);
		menu.setCSSClasses("breadcrumb");
		menu.add("dashboard");
		menu.add(HuntShowPage.commonName);
		menu.add(StepEditPage.commonName);
		menu.add(getName());
	}

	public void setMenuParameters(Enigma enigma) {
		PageMenuItem pmi;

		// This edit page
		pmi = menu.getPage(name);
		fillLink(pmi, enigma);

		// The step edit page
		Step step = enigma.getStep();
		pmi = menu.getPage(StepEditPage.commonName);
		StepEditPage.fillLink(pmi, step);
		pmi.setLabel(Messages.get("pages.stepNumbered", step.getNumber()));

		// The hunt show page
		pmi = menu.getPage(HuntShowPage.commonName);
		HuntShowPage.fillLink(pmi, step.getHunt());
	}

	public static void fillLink(PageLink link, Enigma enigma) {
		link.setRoute(routes.EnigmaController.update(enigma.getId()));
		link.setLabel(Messages.get("pages.enigmaeditNumbered", enigma.getNumber()));
	}
}
