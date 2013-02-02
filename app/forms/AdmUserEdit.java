package forms;

import play.data.validation.Constraints.*;

public class AdmUserEdit {
	/*
	@Required
	@LoginNotAlreadyUsed(message = "Ce pseudonyme est déjà utilisé.")
	public String pseudonym;

	@Required
	public String password;
	*/
	@Required
	public String roleName;
}
