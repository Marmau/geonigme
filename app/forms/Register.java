package forms;

import play.data.validation.Constraints.*;

public class Register {
	@Required
	public String pseudonym;

	@Required
	public String password;

	@Required
	@Email
	public String email;
}
