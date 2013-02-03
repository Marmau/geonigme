package global;

import controllers.routes;
import models.Right;
import play.api.mvc.Call;

public class AdmUserEditPage extends Page {
	
	private String uid = "";

	public AdmUserEditPage(String name, String title, Call route, Right accessRight) throws Exception {
		super(name, title, route, accessRight);
	}
	
	public AdmUserEditPage(String title, Right accessRight) throws Exception {
		super("useredit", title, null, accessRight);
	}
	
	public Call getRoute() {
		return routes.AdminPanelController.useredit(uid);
	}
	
	public void setParameters(String uid) {
		this.uid = uid;
	}

}
