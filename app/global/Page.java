package global;

import java.util.Hashtable;

import play.api.mvc.Call;

import controllers.UserController;
import models.Right;
import models.User;

public class Page implements MenuItem {
/*
	public static final Right USER_LIST = Right.USER_LIST;
	public static final Right USER_EDIT = Right.USER_LIST.AND(Right.USER_LIST);
*/
	private static Hashtable<String, Page> instances = new Hashtable<String, Page>();
	
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
	
	private String title;
	private String name;
	private Call route;
	private Right accessRight;
	
	public Page(String name, String title, Call route, Right accessRight) throws Exception {
		if( instances.containsKey(name) ) {
			throw new Exception("Another page with this name \""+name+"\" already exist.");
		}
		this.name = name;
		this.title = title;
		this.route = route;
		this.accessRight = accessRight;
		instances.put(name, this);
	}

	public boolean userCanAccess() {
		if( accessRight == null ) {
			return true;
		}
		User user = UserController.getLoggedUser();
		// User can be not loggued in to access to 0 right pages.
		//return accessRight.v() == 0 || ( user != null && user.getRole().canDo(accessRight) );
		// User should be loggued in to access to 0 right pages. Use Right.NONE to give access to everybody
		return user != null && user.getRole().canDo(accessRight);
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
