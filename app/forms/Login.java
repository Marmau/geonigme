package forms;

import org.apache.commons.codec.digest.DigestUtils;
import org.openrdf.repository.object.ObjectConnection;

import forms.Constraints.AuthenticationToken;
import global.Sesame;
import play.data.validation.Constraints.Required;

public class Login {
	public static final String ERROR = "Pseudonyme ou mot de passe invalide.";

	@AuthenticationToken
	public String token;

	@Required
	public String login;

	public String password;

	public String validate() {
		ObjectConnection oc = Sesame.getObjectConnection();

		try {
			String uid = login.toLowerCase();
			models.User u = oc.getObject(models.User.class, models.User.URI + uid);

			if (u.getPasswordSha1Hash().equals(DigestUtils.sha256Hex(password))) {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ERROR;
	}
}
