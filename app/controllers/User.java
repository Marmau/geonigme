package controllers;

import forms.Login;
import forms.Register;
import global.Sesame;

import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.codec.digest.DigestUtils;
import org.openrdf.query.MalformedQueryException;
import org.openrdf.query.QueryEvaluationException;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.object.ObjectConnection;

import play.data.Form;
import play.mvc.*;

public class User extends Controller {

	static String userSessionKey = "user";
	
	public static Result logout() {
		session().remove(userSessionKey);
		
		return redirect(routes.Application.index());
	}

	public static Result submitLoginForm() throws DatatypeConfigurationException, RepositoryException, QueryEvaluationException, MalformedQueryException {
		Form<forms.Login> formLogin = form(forms.Login.class).bindFromRequest();

		if (formLogin.hasErrors()) {
			return badRequest(views.html.global.login.render(formLogin, form(Register.class)));
		} else {
			ObjectConnection oc = Sesame.getObjectConnection();

			forms.Login form = formLogin.get();
			
			String uid = form.login.toLowerCase();
			models.User user = oc.getObject(models.User.class, models.User.URI + uid);
			
			GregorianCalendar gcal = (GregorianCalendar) GregorianCalendar.getInstance();
			XMLGregorianCalendar now = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
			user.setLastLoginTime(now);
			
			oc.addObject(user.getResource(), user);
			
			session("user", user.getId());
			
			return redirect(routes.Manager.dashboard());
		}
	}
	
	public static Result submitRegisterForm() throws RepositoryException, DatatypeConfigurationException {
		Form<forms.Register> formRegister= form(forms.Register.class).bindFromRequest();
		
		if (formRegister.hasErrors()) {
			return badRequest(views.html.global.login.render(form(Login.class), formRegister));
		} else {
			ObjectConnection oc = Sesame.getObjectConnection();
			
			forms.Register form = formRegister.get();
			models.User newUser = new models.User();
			GregorianCalendar gcal = (GregorianCalendar) GregorianCalendar.getInstance();
			XMLGregorianCalendar now = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
			newUser.setInscriptionDate(now);
			newUser.setLastLoginTime(now);
			newUser.setLoginName(form.pseudonym);
			newUser.setMail(form.email);
			newUser.setPasswordSha1Hash(DigestUtils.sha256Hex(form.password));

			String uid = newUser.getLoginName().toLowerCase();
			oc.addObject(models.User.URI + uid, newUser);
			
			session("user", uid);
			
			return redirect(routes.Manager.dashboard());
		}
	}
	
	
	public static boolean isLogged() {
		return session(userSessionKey) != null;
	}

	public static Result login() {
		String user = session("user");
		if (user != null)
			return ok();// views.html.dashboard.mainDashboard.render());

		Form<Login> formLogin = form(Login.class);
		Form<Register> formRegister = form(Register.class);

		return ok(views.html.global.login.render(formLogin, formRegister));
	}
}
