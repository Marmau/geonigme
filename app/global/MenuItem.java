package global;

public interface MenuItem extends Linkable {
	
	public String getName();
	public String getTitle();
	public String getUrl();
	public boolean userCanAccess();
	public String getReachableUrl();
}
