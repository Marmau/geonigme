package pages;

import play.i18n.Messages;
import models.Enigma;

public class EnigmaResultPage extends EnigmaPlayPage {

	public EnigmaResultPage(String name, String title, String startJS) throws Exception {
		super(name, title, startJS);
		menu.add(new EnigmaResultPage(this));// Copy it
	}

	public EnigmaResultPage(EnigmaResultPage other) throws Exception {
		super(other);
	}

	// The items of the menu could (should) be a copy
	public void setMenuParameters(Enigma enigma) {
		super.setMenuParameters(enigma);
		// This play page
		EnigmaResultPage p4 = (EnigmaResultPage) menu.getPage(3);// Clone
		p4.setLabel(Messages.get("pages.enigmaSummary", enigma.getNumber()));
	}
}
