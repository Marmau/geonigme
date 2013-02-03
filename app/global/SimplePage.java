package global;

import models.Right;
import play.api.mvc.Call;

public class SimplePage extends Page {

	public SimplePage(String name, String title, Call route, Right accessRight) throws Exception {
		super(name, title, route, accessRight);
	}
}
