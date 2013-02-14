package pages;

import controllers.routes;
import play.api.mvc.Call;

public class HuntCreatePage extends DashboardPage {

	public static final String commonName = "huntcreate";

	public HuntCreatePage(String title, String startJS) throws Exception {
		super(commonName, title, null, startJS);
		menu.setCSSClasses("breadcrumb");
		menu.add("dashboard");
		menu.add(getName());
	}

	@Override
	public Call getRoute() {
		return routes.HuntController.create();
	}
}
