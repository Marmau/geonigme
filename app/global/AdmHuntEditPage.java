package global;

import controllers.routes;
import models.Right;
import play.api.mvc.Call;

public class AdmHuntEditPage extends Page {
	
	private String hid = "";

	public AdmHuntEditPage(String name, String title, Call route, Right accessRight) throws Exception {
		super(name, title, route, accessRight);
	}
	
	public AdmHuntEditPage(String title, Right accessRight) throws Exception {
		super("huntedit", title, null, accessRight);
	}
	
	public Call getRoute() {
		return routes.AdminPanelController.huntedit(hid);
	}
	
	public void setParameters(String hid) {
		this.hid = hid;
	}

}
