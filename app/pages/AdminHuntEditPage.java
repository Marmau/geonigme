package pages;

import controllers.routes;
import models.Hunt;
import models.Right;
import play.api.mvc.Call;

public class AdminHuntEditPage extends AdminPanelPage {

	protected Hunt hunt = null;
	public static final String commonName = "adminhuntedit";

	public AdminHuntEditPage(String title, Right accessRight, String startJS) throws Exception {
		super(commonName, title, null, accessRight, startJS);
		menu.setCSSClasses("breadcrumb");
		menu.add("adminhuntlist");
		menu.add(new AdminHuntEditPage(this));// Copy it
	}

	public AdminHuntEditPage(AdminHuntEditPage other) throws Exception {
		super(other);
	}

	public AdminHuntEditPage setMyParameters(Hunt hunt) {
		this.hunt = hunt;
		return this;
	}

	// The items of the menu could (should) be a copy
	public void setMenuParameters(Hunt hunt) {
		AdminHuntEditPage p = (AdminHuntEditPage) menu.getPage(name);// Should
																		// be a
																		// copy
		p.setMyParameters(hunt);
	}

	@Override
	public Call getRoute() {
		return routes.AdminPanelController.huntedit(hunt.getId());
	}

	public static AdminHuntEditPage getCopy() throws Exception {
		return new AdminHuntEditPage((AdminHuntEditPage) get(commonName));
	}

	public static String getReachableUrl(Hunt hunt) {
		try {
			AdminHuntEditPage page = getCopy();
			page.setMyParameters(hunt);
			return page.getReachableUrl();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
}
