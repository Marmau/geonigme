package forms;

import play.data.validation.Constraints.Required;

public class Hunt {
	@Required
	public String name;

	@Required
	public Integer level = 1;

	public String tags;
	
	public String description;
	
	@Required
	public String area;
}
