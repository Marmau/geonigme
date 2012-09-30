package forms;

import play.data.validation.Constraints.Required;

public class Step {

	public String description;
	
	@Required
	public String position;
	
	@Required
	public String accuracy;
}
