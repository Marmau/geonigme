package global;

import play.mvc.Action;
import play.mvc.Http.Context;
import play.mvc.Result;

public class PageAction extends Action<AssociatedPage> {

	@Override
	public Result call(Context ctx) throws Throwable {
		// For Play <2.1, with 2.1+, it will be obsolete (only useless ?).
		Context.current.set(ctx);
		CurrentRequest.displayPageCount();
		/*
		String uid = ctx.session().get("user");
		if (uid == null) {
			return null;
		}
		System.out.println("Received uid: "+uid);
		//requestMock = mock(Request.class); 
        //cookiesMock = mock(Http.Cookies.class);
		Request request = new DummyRequest();
		System.out.println("PageAction page: "+configuration.value());
		//final Request request = (Request) new FakeRequest().getWrappedRequest();
		Context.current.set(new Context(request, new HashMap <String, String>(), 
		        new HashMap <String, String>()));
		*/
		Page page = Page.get(configuration.value());
		CurrentRequest.setCurrentPage(page);
		if( !page.userCanAccess() ) {
			System.out.println("PageAction> Access forbidden.");
			return forbidden();
		}
		//if( configuration.value() ) {
			//Logger.info("Calling action for " + ctx);  
		//}
		System.out.println("Running request");
		Result result = delegate.call(ctx);
		CurrentRequest.removePage(ctx.request());
		System.out.println("Ending request");
		return result;
	}
}
