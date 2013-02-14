package global;

import play.api.mvc.Call;
import play.api.templates.Html;

public class PageLink implements Linkable {
	
	protected Page page;
	protected Call route;
	protected Html label;
	
	public PageLink(Page page) throws Exception {
		if( page == null ) {
			throw new Exception("PageLink() page argument is NULL");
		}
		this.page = page;
		setRoute(page.getRoute());
		setLabel(page.getTitle());
	}

	public boolean userCanAccess() {
		return page.userCanAccess();
	}

	public String getUrl() {
		return (route != null) ? route.url() : "";
	}

	@Override
	public String getReachableUrl() {
		return (userCanAccess()) ? getUrl() : "";
	}
	
	@Override
	public Html getLabel() {
		return label;
	}

	public Page getPage() {
		return page;
	}

	public void setRoute(Call route) {
		this.route = route;
	}

	public void setLabel(String label) {
		this.label = new Html(label);
	}
	
	public static PageLink getFor(String pageName) throws Exception {
		return new PageLink(Page.get(pageName));
	}
	
	public boolean equals(PageMenuItem other) {
		if( other == null ) {
			return false;
		}
		return getPage().equals(other.getPage());
	}
	
	public boolean equals(Object other) {
		return equals((PageMenuItem) other);
	}
}
