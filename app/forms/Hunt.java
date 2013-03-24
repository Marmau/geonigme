package forms;

import forms.Constraints.AuthenticationToken;
import forms.Constraints.TagWellFormed;
import play.data.validation.Constraints.MaxLength;
import play.data.validation.Constraints.Required;

public class Hunt {
	@AuthenticationToken
	public String token;

	@MaxLength(value = 50, message = "nbMaxCharactersHunt")
	@Required(message = "huntHaveName")
	public String label;
	
	@Required
	public String language;

	@Required
	public Integer level = 1;

	@TagWellFormed(message = "tagOnlyLetters")
	@MaxLength(value = 100, message = "tooTags")
	public String tags;

	public String description;

	@Required
	public String area;
	
	public String delete;
}
