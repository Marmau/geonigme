package forms;

import forms.Constraints.LoginNotAlreadyUsed;
import play.data.validation.Constraints.*;

public class Register {
	@Required
	@LoginNotAlreadyUsed(message = "Ce pseudonyme est déjà utilisé.")
	public String pseudonym;

	@Required
	public String password;

	@Required
	@Email
	public String email;
}
