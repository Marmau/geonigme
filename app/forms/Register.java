package forms;

import forms.Constraints.AuthenticationToken;
import forms.Constraints.LoginNotAlreadyUsed;
import play.data.validation.Constraints.*;

public class Register {
	@AuthenticationToken
	public String token;

	@Required
	@LoginNotAlreadyUsed(message = "usedPseudo")
	public String pseudonym;

	@Required
	public String password;

	@Required
	@Email
	public String email;
}
