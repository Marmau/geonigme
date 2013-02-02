package forms;

import forms.Constraints.AuthenticationToken;
import forms.Constraints.TagWellFormed;
import play.data.validation.Constraints.MaxLength;
import play.data.validation.Constraints.Required;

public class Hunt {
	@AuthenticationToken
	public String token;
	
	@MaxLength(value = 50, message = "Le nom de votre chasse ne peut dépasser 50 caractères.")
	@Required(message = "Vous devez spécifier un nom à votre chasse.")
	public String label;

	@Required
	public Integer level = 1;

	@TagWellFormed(message = "Un tag n'est composé que de lettres, et ne dépasse pas 15 lettres.")
	@MaxLength(value = 100, message = "Trop de tags.")
	public String tags;

	public String description;

	@Required
	public String area;
}
