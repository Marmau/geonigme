package global;

import play.api.templates.Html;

public interface Linkable {

	public Html getLabel();

	public String getReachableUrl();
}
