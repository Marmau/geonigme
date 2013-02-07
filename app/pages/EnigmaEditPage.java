package pages;

import models.Enigma;
import models.Hunt;
import models.Step;
import global.Page;
import controllers.routes;
import play.api.mvc.Call;

public class EnigmaEditPage extends DashboardPage {

	protected Enigma enigma = null;
	
	public EnigmaEditPage(String title, String startJS) throws Exception {
		super("enigmaedit", title, null, startJS);
		menu.setCSSClasses("breadcrumb");
		menu.add("dashboard");
		menu.add(new HuntShowPage((HuntShowPage) Page.get("huntshow")));//Copy it
		menu.add(new StepEditPage((StepEditPage) Page.get("stepedit")));//Copy it
		menu.add(new EnigmaEditPage(this));//Copy it
	}
	
	public EnigmaEditPage(EnigmaEditPage other) throws Exception {
		super(other);
	}
	
	public void setMyParameters(Enigma enigma) {
		this.enigma = enigma;
	}

	// The items of the menu could (should) be a copy
	public void setMenuParameters(Enigma enigma) {
		// This edit page
		EnigmaEditPage p4 = (EnigmaEditPage) menu.getPage(name);// Should be a copy
		p4.setMyParameters(enigma);
		
		// The step edit page
		Step step = enigma.getStep();
		StepEditPage p3 = (StepEditPage) menu.getPage("stepedit");// Should be a copy
		p3.setTitle("Étape "+step.getNumber());
		p3.setMyParameters(step);
		
		// The hunt show page
		Hunt hunt = step.getHunt();
		HuntShowPage p2 = (HuntShowPage) menu.getPage("huntshow");// Should be a copy
		p2.setTitle(hunt.getLabel());
		p2.setMyParameters(hunt);
	}
	
	@Override
	public Call getRoute() {
		return routes.EnigmaController.update(enigma.getId());
	}
}
