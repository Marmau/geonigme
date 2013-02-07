package pages;

import controllers.routes;
import models.Right;
import models.User;
import play.api.mvc.Call;

public class AdminUserEditPage extends AdminPanelPage {

	protected User user = null;
	public static final String commonName = "adminuseredit";

	public AdminUserEditPage(String title, Right accessRight, String startJS) throws Exception {
		super(commonName, title, null, accessRight, startJS);
		menu.setCSSClasses("breadcrumb");
		menu.add("adminuserlist");
		menu.add(new AdminUserEditPage(this));// Copy it
	}

	public AdminUserEditPage(AdminUserEditPage other) throws Exception {
		super(other);
	}

	public AdminUserEditPage setMyParameters(User user) {
		this.user = user;
		return this;
	}

	// The items of the menu could (should) be a copy
	public void setMenuParameters(User user) {
		AdminUserEditPage p = (AdminUserEditPage) menu.getPage(name);// Should
																		// be a
																		// copy
		p.setMyParameters(user);
	}

	@Override
	public Call getRoute() {
		return routes.AdminPanelController.useredit(user.getId());
	}

	public static AdminUserEditPage getCopy() throws Exception {
		return new AdminUserEditPage((AdminUserEditPage) get(commonName));
	}

	public static String getReachableUrl(User user) {
		try {
			AdminUserEditPage page = getCopy();
			page.setMyParameters(user);
			return page.getReachableUrl();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

}
