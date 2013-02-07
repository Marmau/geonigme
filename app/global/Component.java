package global;

import play.api.templates.Html;

public class Component {

	// Prefer to use includes in templates
	public static Html link(Linkable o, Html Content) {
		return views.html.components.link.render(o, Content);
	}

	public static Html link(Linkable o) {
		return link(o, o.getLabel());
	}
}
