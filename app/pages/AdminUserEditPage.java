package pages;

import global.Page;
import controllers.routes;
import models.Right;
import play.api.mvc.Call;

public class AdminUserEditPage extends Page {
	
	protected String uid = null;
	
	public AdminUserEditPage(String title, Right accessRight, String startJS) throws Exception {
		super("useredit", title, null, accessRight, startJS);
		menu.add("userlist");
		menu.add(new AdminUserEditPage(this));//Copy it
	}
	
	public AdminUserEditPage(AdminUserEditPage other) throws Exception {
		super(other);
	}
	
	public void setMyParameters(String uid) {
		this.uid = uid;
	}

	// The entry in the menu could (should) be a copy
	public void setMenuParameters(String uid) {
		AdminUserEditPage p = (AdminUserEditPage) menu.getPage(name);// Should be a copy of this one
		p.setMyParameters(uid);
	}
	
	@Override
	public Call getRoute() {
		return routes.AdminPanelController.useredit(uid);
	}

}
