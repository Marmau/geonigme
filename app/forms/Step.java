package forms;

import forms.Constraints.AuthenticationToken;
import play.data.validation.Constraints.Required;

public class Step {

	@AuthenticationToken
	public String token;

	public String description;

	@Required
	public String position;

	@Required
	public Float accuracy;
	
	public String delete;
}
