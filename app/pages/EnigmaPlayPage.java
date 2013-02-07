package pages;

import global.Page;
import models.Enigma;
import models.Hunt;
import models.Step;
import controllers.routes;
import play.api.mvc.Call;

public class EnigmaPlayPage extends GamePage {

	protected EnigmaPlayPage(String name, String title, String startJS) throws Exception {
		super(name, title, null, startJS);
		menu.setCSSClasses("breadcrumb");
		menu.add("huntlist");
		menu.add(new HuntPlayPage((HuntPlayPage) Page.get("huntplay")));//Copy it
		menu.add(new HuntPlayPage((HuntPlayPage) Page.get("huntplay")));//Copy it
	}
	public EnigmaPlayPage(String title, String startJS) throws Exception {
		this("enigmaplay", title, startJS);
		menu.add(new EnigmaPlayPage(this));//Copy it
	}
	
	public EnigmaPlayPage(EnigmaPlayPage other) throws Exception {
		super(other);
	}

	// The items of the menu could (should) be a copy
	public void setMenuParameters(Enigma enigma) {
		// This play page
		EnigmaPlayPage p4 = (EnigmaPlayPage) menu.getPage(3);// Should be a copy
		p4.setTitle("Énigme "+enigma.getNumber());
		
		// The step play page
		Step step = enigma.getStep();
		Hunt hunt = step.getHunt();
		HuntPlayPage p3 = (HuntPlayPage) menu.getPage(2);// Should be a copy
		p3.setMyParameters(hunt);
		p3.setTitle("Étape "+step.getNumber());
		
		// The hunt play page
		HuntPlayPage p2 = (HuntPlayPage) menu.getPage(1);// Should be a copy
		p2.setMyParameters(hunt);
		p2.setTitle(hunt.getLabel());
	}
	
	@Override
	public Call getRoute() {
		return routes.GameController.go();
	}
}
