package global;

import java.util.Hashtable;

import play.api.mvc.Call;
import play.api.templates.Html;

import controllers.UserController;
import models.Right;
import models.User;

public class Page implements MenuItem {
/*
	public static final Right USER_LIST = Right.USER_LIST;
	public static final Right USER_EDIT = Right.USER_LIST.AND(Right.USER_LIST);
*/
	private static Hashtable<String, Page> instances = new Hashtable<String, Page>();
	
	public static Page getCopy(String pageName) throws Exception {
		return new Page(get(pageName));
	}
	
	public static Page get(String pageName) throws Exception {
		Page page = instances.get(pageName);
		if( page == null ) {
			throw new Exception("The page \""+pageName+"\" is unknown.");
		}
		return page;
	}

	// Should really exist ? Or dev should use get and instance method ?
	public static boolean userCanAccess(String pageName) throws Exception {
		return get(pageName).userCanAccess();
	}
	
	//HashMap<String, Page> instances = new HashMap<String, Page>();
	
	protected String title;
	protected String name;
	protected Call route;
	protected Right accessRight;
	protected Menu menu;
	protected String bodyClasses;
	protected String startJS;

	private Page(String name, String title, Call route, Right accessRight, String startJS, boolean clone) throws Exception {
		if( !clone && instances.containsKey(name) ) {
			throw new Exception("Another page with this name \""+name+"\" already exist.");
		}
		this.name = name;
		this.title = title;
		this.route = route;
		this.accessRight = accessRight;
		this.startJS = startJS;
		this.menu = new Menu(false);// Empty menu
		this.bodyClasses = "";
		if( !clone ) {
			instances.put(name, this);
		}
	}
	public Page(String name, String title, Call route, Right accessRight, String startJS) throws Exception {
		this(name, title, route, accessRight, startJS, false);
	}
	// Clone it !
	public Page(Page other) throws Exception {
		this(other.name, other.title, other.route, other.accessRight, other.startJS, true);
	}

	public boolean userCanAccess() {
		if( accessRight == null ) {
			return true;
		}
		User user = UserController.getLoggedUser();
		if( user == null ) {
			System.out.println("Page.userCanAccess> User is null");
		}
		// User can be not loggued in to access to 0 right pages.
		//return accessRight.v() == 0 || ( user != null && user.getRole().canDo(accessRight) );
		// User should be loggued in to access to 0 right pages. Use Right.NONE to give access to everybody
		return user != null && user.getRole().canDo(accessRight);
	}
	
	/*
	 * We prefer to clone pages.
	// Only for the current request (loading page)
	public void setParameter(String paramName, String paramValue) {
		CurrentRequest.instance().setPageParameter(name, paramName, paramValue);
	}

	// Only for the current request (loading page)
	public String getParameter(String paramName) {
		return CurrentRequest.instance().getPageParameter(name, paramName);
	}
	*/
	
	public String getStartJS() {
		return bodyClasses;
	}
	
	public String getBodyClasses() {
		return bodyClasses;
	}
	
	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	
	public Menu getMenu() {
		return menu;
	}

	public Html renderMenu() {
		return getMenu().render(this);
	}

	public String getTitle() {
		return title;
	}
	
	public String getName() {
		return name;
	}

	public Call getRoute() {
		return route;
	}
	
	public String getUrl() {
		return getRoute().url();
	}

	public Right getAccessRight() {
		return accessRight;
	}
	
	public boolean equals(Page other) {
		if( other == null ) {
			return false;
		}
		return other.getName().equals(getName());
	}
	public boolean equals(Object other) {
		return equals((Page) other);
	}

}
