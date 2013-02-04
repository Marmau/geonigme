package global;

import java.util.ArrayList;

import play.api.templates.Html;

public class Menu {
	
	public static final Menu adminPanelMenu = new Menu();
	public static final Menu dashboardMenu = new Menu();
	public static final Menu memberMenu = new Menu("dropdown-menu");
	
	static {
		// Put all menus' configuration below
		// Use an existing menu, declared above.

		// Member's Global Menu
		memberMenu.add("home");
		memberMenu.add("dashboard");
		memberMenu.add("logout");
		
		// Admin Panel Menu
		adminPanelMenu.add("userlist");
		
		// Dashboard Menu
	}

	private String cssClasses = "";
	private ArrayList<MenuItem> items;
	
	public Menu(String cssClasses) {
		this.cssClasses = cssClasses;
		items = new ArrayList<MenuItem>();
	}
	
	public Menu() {
		this("menu");
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
	
	public String getCSSClasses() {
		return cssClasses;
	}
	
	public Html render(MenuItem currentItem) {
		return views.html.global.menu.render(this, currentItem);
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
	
	public Html render() {
		return render((MenuItem) null);
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
