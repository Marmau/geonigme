package forms;

import forms.Constraints.AuthenticationToken;
import play.data.validation.Constraints.*;

public class ContactUs {
	@AuthenticationToken
	public String token;
	
	@Required
	@Email
	public String email;

	@Required
	public String content;
}
