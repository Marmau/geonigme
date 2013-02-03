package global;

import java.util.ArrayList;

import play.api.templates.Html;

public class Menu {
	
	public static final Menu adminPanelMenu = new Menu();
	public static final Menu dashboardMenu = new Menu();
	
	static {
		// Put all menus' configuration below
		// Use an existing menu, declared above.
		
		// Admin Panel Menu
		adminPanelMenu.add("userlist");
		
		// Dashboard Menu
	}

	private ArrayList<MenuItem> items;
	//public final String cssClass = "menu";
	
	public Menu() {
		items = new ArrayList<MenuItem>();
	}
	
	public Menu add(String pageName) {
		try {
			add(Page.get(pageName));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public Menu add(MenuItem item) {
		items.add(item);
		return this;
	}
	
	public ArrayList<MenuItem> getItems() {
		return items;
	}
	
	public Html render(String currentPageName) {
		Page page = null;
		try {
			page = Page.get(currentPageName);
		} catch (Exception e) {
			e.printStackTrace();
			//return new Html("Page \""+currentPageName+"\" not found, menu not available");
		}
		return render(page);
	}
	
	public Html render(MenuItem currentItem) {
		return views.html.global.menu.render(this, currentItem);
	}
	
	/*
	<ul class="menu">
		<li class="selected"><a href="@routes.AdminPanelController.userlist()">Utilisateurs</a></li>
		<li><a class="pushable" href="@routes.AdminPanelController.userlist()">Chasses</a></li>
		<li><a class="pushable" href="">Autre</a></li>
	</ul>
	<ul class="breadcrumb">
		<li><a href="@routes.ManagerController.dashboard()">Tableau de bord</a></li>
		<li><a href="@routes.HuntController.show(step.getHunt().getId())"><span class="hunt-name">@step.getHunt().getLabel()</span></a></li>
		<li><a href="@routes.HuntController.show(step.getHunt().getId())">&Eacute;tape @step.getNumber()</a></li>
		<li><a>Nouvelle énigme</a></li>
	</ul>
	 */
}
