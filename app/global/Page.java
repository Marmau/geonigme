package global;

import java.util.Hashtable;

import forms.ContactUs;

import play.api.mvc.Call;
import play.api.templates.Html;
import play.data.Form;
import repository.UserRepository;

import models.Right;
import models.User;

public class Page implements MenuItem {

	private static Hashtable<String, Page> instances = new Hashtable<String, Page>();

	/*
	 * // Don't make a copy of child classes. public static Page getCopy(String
	 * pageName) throws Exception { return new Page(get(pageName)); }
	 */

	public static Page get(String pageName) throws Exception {
		Page page = instances.get(pageName);
		if (page == null) {
			throw new Exception("The page \"" + pageName + "\" is unknown.");
		}
		return page;
	}

	// Should really exist ? Or dev should use get and instance method ?
	public static boolean userCanAccess(String pageName) throws Exception {
		return get(pageName).userCanAccess();
	}

	// Should really exist ? Or dev should use get and instance method ?
	public static String getReachableURL(String pageName) throws Exception {
		Page page = get(pageName);
		if (page == null) {
			return "";
		}
		return page.getReachableUrl();
	}

	// HashMap<String, Page> instances = new HashMap<String, Page>();

	protected String title;
	protected String name;
	protected Call route;
	protected Right accessRight;
	protected Menu menu;
	protected String bodyClasses;
	protected String startJS;

	private Page(String name, String title, Call route, Right accessRight, String startJS, boolean clone)
			throws Exception {
		if (!clone && instances.containsKey(name)) {
			throw new Exception("Another page with this name \"" + name + "\" already exist.");
		}
		this.name = name;
		this.title = title;
		this.route = route;
		this.accessRight = accessRight;
		this.startJS = startJS;
		this.menu = new Menu(false);// Empty menu
		this.bodyClasses = "";
		if (!clone) {
			instances.put(name, this);
		}
		setup();
	}

	public Page(String name, String title, Call route, Right accessRight, String startJS) throws Exception {
		this(name, title, route, accessRight, startJS, false);
	}

	// Clone it !
	public Page(Page other) throws Exception {
		this(other.name, other.title, other.route, other.accessRight, other.startJS, true);
	}

	// Do nothing here
	protected void setup() {
	}

	public boolean userCanAccess() {
		if (accessRight == null) {
			return true;
		}
		User user = UserRepository.getLoggedUser();
		// User can be not logged in to access to 0 right pages.
		// return accessRight.v() == 0 || ( user != null &&
		// user.getRole().canDo(accessRight) );
		// User should be logged in to access to 0 right pages. Use Right.NONE
		// to give access to everybody
		return user != null && user.getRole().canDo(accessRight);
	}

	/*
	 * We prefer to clone pages. // Only for the current request (loading page)
	 * public void setParameter(String paramName, String paramValue) {
	 * CurrentRequest.instance().setPageParameter(name, paramName, paramValue);
	 * }
	 * 
	 * // Only for the current request (loading page) public String
	 * getParameter(String paramName) { return
	 * CurrentRequest.instance().getPageParameter(name, paramName); }
	 */

	public String getStartJS() {
		return startJS;
	}

	public String getBodyClasses() {
		return bodyClasses;
	}

	public void setMenu(Menu menu) {
		// We defined this menu as main of this page only if this page does not
		// set a menu yet.
		if (this.menu == null || this.menu.isEmpty()) {
			this.menu = menu;
		}
	}

	public Menu getMenu() {
		return menu;
	}

	public Html renderMenu() {
		return getMenu().render(this);
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public Html getLabel() {
		return new Html(title);
	}

	public String getName() {
		return name;
	}

	public Call getRoute() {
		return route;
	}

	public String getUrl() {
		/*
		 * if( getRoute() != null ) {
		 * System.out.println("Route of "+getName()+" is NOT null"); } else {
		 * System.out.println("Route of "+getName()+" IS NULL"); }
		 */
		return (getRoute() != null) ? getRoute().url() : "";
	}

	public String getReachableUrl() {
		/*
		 * if( userCanAccess() ) {
		 * System.out.println("User can access to page "+getName()); } else {
		 * System.out.println("User can NOT access to page "+getName()); }
		 * System.out.println("URL is "+getUrl());
		 */
		return (userCanAccess()) ? getUrl() : "";
	}

	public Right getAccessRight() {
		return accessRight;
	}

	public boolean equals(Page other) {
		if (other == null) {
			return false;
		}
		return other.getName().equals(getName());
	}

	public boolean equals(Object other) {
		return equals((Page) other);
	}
	
	public Form<ContactUs> getFormContact() {
		return new Form<>(ContactUs.class);
	}
}
