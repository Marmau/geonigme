package pages;

import global.Page;
import models.Hunt;
import models.Step;
import controllers.routes;
import play.api.mvc.Call;
import play.i18n.Messages;

public class StepEditPage extends DashboardPage {

	protected Step step = null;

	public StepEditPage(String title, String startJS) throws Exception {
		super("stepedit", title, null, startJS);
		menu.setCSSClasses("breadcrumb");
		menu.add("dashboard");
		menu.add(new HuntShowPage((HuntShowPage) Page.get("huntshow")));// Copy it
		menu.add(new StepEditPage(this));// Copy it
	}

	public StepEditPage(StepEditPage other) throws Exception {
		super(other);
	}

	public void setMyParameters(Step step) {
		this.step = step;
	}

	// The items of the menu could (should) be a copy
	public void setMenuParameters(Step step) {
		// This edit page
		StepEditPage p3 = (StepEditPage) menu.getPage(name);// Clone
		p3.setLabel(Messages.get("pages.stepedit", step.getNumber()));
		p3.setMyParameters(step);

		// The hunt show page
		Hunt hunt = step.getHunt();
		HuntShowPage p2 = (HuntShowPage) menu.getPage("huntshow");// Clone
		p2.setLabel(hunt.getLabel());
		p2.setMyParameters(hunt);
	}

	@Override
	public Call getRoute() {
		return routes.StepController.update(step.getId());
	}
}
