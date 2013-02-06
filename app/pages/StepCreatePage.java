package pages;

import global.Page;
import models.Hunt;
import controllers.routes;
import play.api.mvc.Call;

public class StepCreatePage extends DashboardPage {
	
	protected Hunt hunt = null;
	
	public StepCreatePage(String title, String startJS) throws Exception {
		super("stepcreate", title, null, startJS);
		menu.setCSSClasses("breadcrumb");
		menu.add("dashboard");
		menu.add(new HuntShowPage((HuntShowPage) Page.get("huntshow")));//Copy it
		menu.add(new StepCreatePage(this));//Copy it
	}
	
	public StepCreatePage(StepCreatePage other) throws Exception {
		super(other);
	}
	
	public void setMyParameters(Hunt hunt) {
		this.hunt = hunt;
	}

	// The entry in the menu could (should) be a copy
	public void setMenuParameters(Hunt hunt) {
		// This edit page
		StepCreatePage p3 = (StepCreatePage) menu.getPage(name);// Should be a copy of this one
		p3.setMyParameters(hunt);
		
		// The hunt show page
		HuntShowPage p2 = (HuntShowPage) menu.getPage("huntshow");// Should be a copy of this one
		p2.setTitle(hunt.getLabel());
		p2.setMyParameters(hunt);
	}
	
	@Override
	public Call getRoute() {
		return routes.StepController.create(hunt.getId());
	}
}
