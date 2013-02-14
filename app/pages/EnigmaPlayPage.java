package pages;

import global.PageLink;
import global.PageMenuItem;
import models.Enigma;
import models.Step;
import controllers.routes;
import play.i18n.Messages;

public class EnigmaPlayPage extends GamePage {

	public static final String commonName = "huntplay";

	protected EnigmaPlayPage(String name, String title, String startJS) throws Exception {
		super(name, title, null, startJS);
		menu.setCSSClasses("breadcrumb");
		menu.add("huntlist");
		menu.add(HuntPlayPage.commonName);
		menu.add(StepPlayPage.commonName);
	}

	public EnigmaPlayPage(String title, String startJS) throws Exception {
		this("enigmaplay", title, startJS);
		menu.add(getName());
	}

	// The items of the menu could (should) be a copy
	public void setMenuParameters(Enigma enigma) {
		PageMenuItem pmi;
		
		// This play page
		pmi = menu.getPage(getName());
		fillLink(pmi, enigma);
		
		// The step play page
		Step step = enigma.getStep();
		pmi = menu.getPage(2);
		StepPlayPage.fillLink(pmi, step);
		
		// The hunt play page
		pmi = menu.getPage(1);
		HuntPlayPage.fillLink(pmi, step.getHunt());
	}

	public static void fillLink(PageLink link, Enigma enigma) {
		link.setRoute(routes.GameController.go());
		link.setLabel(Messages.get("pages.enigmaNumbered", enigma.getNumber()));
	}
}
