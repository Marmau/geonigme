package pages;

import models.Hunt;
import models.Step;
import global.Page;
import controllers.routes;
import play.api.mvc.Call;

public class EnigmaCreatePage extends DashboardPage {

	protected Step step = null;
	
	public EnigmaCreatePage(String title, String startJS) throws Exception {
		super("enigmacreate", title, null, startJS);
		menu.setCSSClasses("breadcrumb");
		menu.add("dashboard");
		menu.add(new HuntShowPage((HuntShowPage) Page.get("huntshow")));//Copy it
		menu.add(new StepEditPage((StepEditPage) Page.get("stepedit")));//Copy it
		menu.add(new EnigmaCreatePage(this));//Copy it
	}
	
	public EnigmaCreatePage(EnigmaCreatePage other) throws Exception {
		super(other);
	}
	
	public void setMyParameters(Step step) {
		this.step = step;
	}

	// The entry in the menu could (should) be a copy
	public void setMenuParameters(Step step) {
		// This edit page
		EnigmaCreatePage p4 = (EnigmaCreatePage) menu.getPage(name);// Should be a copy of this one
		p4.setMyParameters(step);
		
		// The step edit page
		StepEditPage p3 = (StepEditPage) menu.getPage("stepedit");// Should be a copy of this one
		p3.setTitle("Étape "+step.getNumber());
		p3.setMyParameters(step);
		
		// The hunt show page
		Hunt hunt = step.getHunt();
		HuntShowPage p2 = (HuntShowPage) menu.getPage("huntshow");// Should be a copy of this one
		p2.setTitle(hunt.getLabel());
		p2.setMyParameters(hunt);
	}
	
	@Override
	public Call getRoute() {
		return routes.EnigmaController.update(step.getId());
	}
}
