package pages;

import models.Hunt;
import models.Step;
import global.Page;
import controllers.routes;
import play.api.mvc.Call;
import play.i18n.Messages;

public class EnigmaCreatePage extends DashboardPage {

	protected Step step = null;

	public EnigmaCreatePage(String title, String startJS) throws Exception {
		super("enigmacreate", title, null, startJS);
		menu.setCSSClasses("breadcrumb");
		menu.add("dashboard");
		menu.add(new HuntShowPage((HuntShowPage) Page.get("huntshow")));// Copy
																		// it
		menu.add(new StepEditPage((StepEditPage) Page.get("stepedit")));// Copy
																		// it
		menu.add(new EnigmaCreatePage(this));// Copy it
	}

	public EnigmaCreatePage(EnigmaCreatePage other) throws Exception {
		super(other);
	}

	public void setMyParameters(Step step) {
		this.step = step;
	}

	// The items of the menu could (should) be a copy
	public void setMenuParameters(Step step) {
		// This edit page
		EnigmaCreatePage p4 = (EnigmaCreatePage) menu.getPage(name);// Clone
		p4.setMyParameters(step);

		// The step edit page
		StepEditPage p3 = (StepEditPage) menu.getPage("stepedit");// Clone
		p3.setLabel(Messages.get("pages.stepNumbered", step.getNumber()));
		p3.setMyParameters(step);

		// The hunt show page
		Hunt hunt = step.getHunt();
		HuntShowPage p2 = (HuntShowPage) menu.getPage("huntshow");// Clone
		p2.setLabel(hunt.getLabel());
		p2.setMyParameters(hunt);
	}

	@Override
	public Call getRoute() {
		return routes.EnigmaController.update(step.getId());
	}
}
