package pages;

import global.PageLink;
import global.PageMenuItem;
import play.i18n.Messages;
import models.Enigma;

public class EnigmaResultPage extends EnigmaPlayPage {

	public EnigmaResultPage(String name, String title, String startJS) throws Exception {
		super(name, title, startJS);
		// Super added other items
		menu.add(getName());// Copy it
	}

	// The items of the menu could (should) be a copy
	public void setMenuParameters(Enigma enigma) {
		super.setMenuParameters(enigma);
		
		PageMenuItem pmi;
		
		// This play page
		pmi = menu.getPage(3);
		fillLink(pmi, enigma);
	}

	public static void fillLink(PageLink link, Enigma enigma) {
		//link.setRoute(routes.GameController.go());
		link.setLabel(Messages.get("pages.enigmaSummary", enigma.getNumber()));
	}
}
