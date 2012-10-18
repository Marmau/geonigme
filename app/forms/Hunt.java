package forms;

import play.data.validation.Constraints.Required;

public class Hunt {
	@Required(message = "Vous devez spécifier un nom à votre chasse.")
	public String label;

	@Required
	public Integer level = 1;

	public String tags;

	public String description;

	@Required
	public String area;
}
