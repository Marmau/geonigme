package forms;

import play.data.validation.Constraints.Required;

public class Enigma {
	@Required
	public String description;

	@Required
	public Integer type = 1;

	public String answers;
	
	public String clues;

}
