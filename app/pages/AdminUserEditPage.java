package pages;

import global.PageLink;
import global.PageMenuItem;
import controllers.routes;
import models.Right;
import models.User;

public class AdminUserEditPage extends AdminPanelPage {

	public static final String commonName = "adminuseredit";

	public AdminUserEditPage(String title, Right accessRight, String startJS) throws Exception {
		super(commonName, title, null, accessRight, startJS);
		menu.setCSSClasses("breadcrumb");
		menu.add("adminuserlist");
		menu.add(getName());// Copy it
	}

	public void setMenuParameters(User user) {
		PageMenuItem pmi2 = menu.getPage(getName());
		fillLink(pmi2, user);
	}

	public static PageLink fillLink(PageLink link, User user) {
		link.setRoute(routes.AdminPanelController.useredit(user.getId()));
		return link;
	}
	
	public static PageLink getLinkFor(User user) throws Exception {
		return fillLink(PageLink.getFor(commonName), user);
	}
}
