package pages;

import global.PageLink;
import global.PageMenuItem;
import controllers.routes;
import models.Hunt;
import models.Right;

public class AdminHuntEditPage extends AdminPanelPage {

	public static final String commonName = "adminhuntedit";

	public AdminHuntEditPage(String title, Right accessRight, String startJS) throws Exception {
		super(commonName, title, null, accessRight, startJS);
		menu.setCSSClasses("breadcrumb");
		menu.add("adminhuntlist");
		menu.add(getName());
	}

	public void setMenuParameters(Hunt hunt) {
		PageMenuItem pmi2 = menu.getPage(getName());
		fillLink(pmi2, hunt);
	}

	public static PageLink fillLink(PageLink link, Hunt hunt) {
		link.setRoute(routes.AdminPanelController.huntedit(hunt.getId()));
		return link;
	}
	
	public static PageLink getLinkFor(Hunt hunt) throws Exception {
		return fillLink(PageLink.getFor(commonName), hunt);
	}
}
