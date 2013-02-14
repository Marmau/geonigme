package global;

public interface MenuItem extends Linkable {

	public String getUrl();
	
	// Used to find an item by name
	public String getName();

	// Used to check user rights
	public boolean userCanAccess();
	
	// Used to clone menus
	public Object clone();

	//public Html getLabel();

	//public String getReachableUrl();
}
