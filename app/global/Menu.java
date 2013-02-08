package global;

import java.util.ArrayList;

import play.api.templates.Html;

public class Menu {

	public static Menu adminPanelMenu = new Menu(true);
	public static Menu dashboardMenu = new Menu(true);
	public static Menu homeMenu = new Menu(true);
	public static Menu gameMenu = new Menu(true);
	public static Menu memberMenu = new Menu(false, "dropdown-menu");

	private boolean isNavMenu;
	private String cssClasses = "";
	private ArrayList<MenuItem> items;

	public Menu(boolean isNavMenu, String cssClasses) {
		this.isNavMenu = isNavMenu;
		this.cssClasses = cssClasses;
		items = new ArrayList<MenuItem>();
	}

	public Menu(boolean isNavMenu) {
		this(isNavMenu, "menu");
	}

	public Menu add(String pageName, boolean isNavMenu) {
		try {
			Page page = Page.get(pageName);
			add(page);
			if (isNavMenu) {
				page.setMenu(this);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}

	public Menu add(String pageName) {
		return add(pageName, isNavMenu);
	}

	public Menu add(MenuItem item) {
		items.add(item);
		return this;
	}

	// Fail if name does no match with a page.
	public Page getPage(String name) {
		for (MenuItem item : items) {
			if (item.getName().equals(name)) {
				return (Page) item;
			}
		}
		return null;
	}

	// Fail if name does no match with a page.
	public Page getPage(int pos) {
		return (Page) items.get(pos);
	}

	public ArrayList<MenuItem> getItems() {
		return items;
	}

	public boolean isEmpty() {
		return items == null || items.isEmpty();
	}

	public String getCSSClasses() {
		return cssClasses;
	}

	public void setCSSClasses(String cssClasses) {
		this.cssClasses = cssClasses;
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
			// return new
			// Html("Page \""+currentPageName+"\" not found, menu not available");
		}
		return render(page);
	}

	public Html render() {
		return render((MenuItem) null);
	}
}
