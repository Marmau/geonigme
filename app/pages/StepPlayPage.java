package pages;

import global.Page;
import models.Hunt;
import models.Step;
import controllers.routes;
import play.api.mvc.Call;
import play.i18n.Messages;

public class StepPlayPage extends GamePage {

	public StepPlayPage(String title, String startJS) throws Exception {
		super("stepplay", title, null, startJS);
		menu.setCSSClasses("breadcrumb");
		menu.add("huntlist");
		menu.add(new HuntPlayPage((HuntPlayPage) Page.get("huntplay")));// Copy
																		// it
		menu.add(new StepPlayPage(this));// Copy it
	}

	public StepPlayPage(StepPlayPage other) throws Exception {
		super(other);
	}

	// The items of the menu could (should) be a copy
	public void setMenuParameters(Step step) {
		// This play page
		StepPlayPage p3 = (StepPlayPage) menu.getPage(name);// Clone
		p3.setLabel(Messages.get("pages.enigmaNumbered", step.getNumber()));

		// The hunt play page
		Hunt hunt = step.getHunt();
		HuntPlayPage p2 = (HuntPlayPage) menu.getPage("huntplay");// Clone
		p2.setMyParameters(hunt);
		p2.setLabel(hunt.getLabel());
	}

	@Override
	public Call getRoute() {
		return routes.GameController.go();
	}
}
