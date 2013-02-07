package global;

import java.util.HashMap;

import play.mvc.Controller;
import play.mvc.Http.Context;
import play.mvc.Http.Request;

public class CurrentRequest extends Controller {

	// Class' attributes
	public static HashMap<Integer, CurrentRequest> loadingPages = new HashMap<Integer, CurrentRequest>();

	// Instance's attributes
	protected Page page;
	protected Request request;
	protected Context context;

	// Linking data with format PageName_ParameterName = ParameterValue (to
	// optimize)
	// protected HashMap<String, String> pageParameters;//We prefer page
	// cloning.

	// Instance's methods
	protected CurrentRequest(Page page, Request request, Context context) {
		this.page = page;
		this.request = request;
		this.context = context;
		// this.pageParameters = new HashMap<String, String>();//We prefer page
		// cloning.
	}

	/*
	 * We prefer page cloning. public void setPageParameter(String pageName,
	 * String paramName, String paramValue) {
	 * this.pageParameters.put(pageName+paramName, paramValue); }
	 * 
	 * public String getPageParameter(String pageName, String paramName) {
	 * return this.pageParameters.get(pageName+paramName); }
	 */

	public void setRequestedPage(Page page) {
		this.page = page;
	}

	public Page getRequestedPage() {
		return page;
	}

	public Request getRequest() {
		return request;
	}

	public Context getContext() {
		return context;
	}

	// Class' methods
	public static void setPage(Page page) {
		if (!loadingPages.containsKey(id())) {
			// If new request, we insert the new page
			loadingPages.put(id(), new CurrentRequest(page, ctx().request(), ctx()));

		} else {
			// Else we update it
			loadingPages.get(id()).setRequestedPage(page);
		}
	}

	public static CurrentRequest instance() {
		return loadingPages.get(id());
	}

	public static HashMap<Integer, CurrentRequest> getLoadingPages() {
		return loadingPages;
	}

	public static Page page() {
		CurrentRequest curr = instance();
		if (curr == null) {
			return null;
		}
		return curr.getRequestedPage();
	}

	public static Request request() {
		CurrentRequest curr = instance();
		if (curr == null) {
			return null;
		}
		return curr.getRequest();
	}

	public static Context context() {
		CurrentRequest curr = instance();
		if (curr == null) {
			return null;
		}
		return curr.getContext();
	}

	public static int id() {
		return (int) Thread.currentThread().getId();
		// return request().hashCode();
	}

	public static void displayID() {
		System.out.println("CurrentRequest id: " + id());
	}

	public static void remove() {
		if (!loadingPages.containsKey(id())) {
			System.out.println("CurrentRequest.removePage> ID to remove NOT FOUND.");
		}
		// System.out.println("CurrentRequest.removePage> Removing requested page.");
		loadingPages.remove(id());
	}
}
