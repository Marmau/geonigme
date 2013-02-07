package global;

import play.mvc.Action;
import play.mvc.Http.Context;
import play.mvc.Result;

public class PageAction extends Action<AssociatedPage> {

	@Override
	public Result call(Context ctx) throws Throwable {
		// For Play <2.1, with 2.1+, it will be obsolete (only useless ?).
		Context.current.set(ctx);
		Page page = Page.get(configuration.value());
		CurrentRequest.setPage(page);
		if( !page.userCanAccess() ) {
			System.out.println("PageAction> Access forbidden.");
			return forbidden();
		}
		//System.out.println("Running request");
		Result result = delegate.call(ctx);
		CurrentRequest.remove();
		//System.out.println("Ending request");
		return result;
	}
}
