package global;

import java.util.UUID;

import play.api.libs.Crypto;
import play.mvc.Http.Session;

public class AuthenticationTokenGenerator {

	public static final String AUTH_TOKEN = "csrf_token";
	
	public static String generate() {
		Session session = play.mvc.Http.Context.current().session();
		String token = session.get(AUTH_TOKEN);
		if (token == null) {
			token = UUID.randomUUID().toString();
			session.put(AUTH_TOKEN, token);
		}
		
		String signedToken = Crypto.sign(token);
		
		return signedToken;
	}

}