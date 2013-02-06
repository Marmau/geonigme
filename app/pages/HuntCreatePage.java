package pages;

import controllers.routes;
import play.api.mvc.Call;

public class HuntCreatePage extends DashboardPage {
	
	public HuntCreatePage(String title, String startJS) throws Exception {
		super("huntcreate", title, null, startJS);
		menu.setCSSClasses("breadcrumb");
		menu.add("dashboard");
		menu.add(new HuntCreatePage(this));//Copy it
	}
	
	public HuntCreatePage(HuntCreatePage other) throws Exception {
		super(other);
	}
	
	@Override
	public Call getRoute() {
		return routes.HuntController.create();
	}
}
