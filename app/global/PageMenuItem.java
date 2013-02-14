package global;

public class PageMenuItem extends PageLink implements MenuItem, Cloneable {
	
	public PageMenuItem(Page page) throws Exception {
		super(page);
	}

	@Override
	public String getName() {
		return page.getName();
	}
	
	public PageMenuItem clone() {
		try {
			return (PageMenuItem) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}

}
