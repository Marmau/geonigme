package global;

import java.util.HashMap;

import play.mvc.Controller;
import play.mvc.Http.Request;

public class CurrentRequest extends Controller {

	public static HashMap<Request, Page> loadingPages = new HashMap<Request, Page>();
	
	public static void test() {
		//System.out.println("CurrentRequest Current thread: "+Thread.currentThread().getId());
		//System.out.println("CurrentRequest Current request: "+AdminPanelController.ctx().request().hashCode());
		
		System.out.println("CurrentRequest id: "+id());
	}
	
	public static void displayPageCount() {
		System.out.println("CurrentRequest> "+loadingPages.size()+" pages loading.");
	}
	
	public static void setCurrentPage(Page page) {
		loadingPages.put(request(), page);
	}
	
	public static void getCurrentPage(Page page) {
		loadingPages.get(request());
	}
	
	public static int id() {
		return request().hashCode();
	}
	
	public static Request request() {
		return ctx().request();
	}

	public static void removePage(Request request) {
		if( loadingPages.containsKey(request) ) {
			System.out.println("CurrentRequest.removePage> Found Request to remove.");
		}
		System.out.println("CurrentRequest.removePage> Removing requested page.");
		loadingPages.remove(request);
	}
}
