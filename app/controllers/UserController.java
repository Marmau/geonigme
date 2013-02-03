package controllers;

import forms.Login;
import forms.Register;
import global.Sesame;

import java.io.StringWriter;

import models.Role;

import org.openrdf.query.QueryLanguage;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.codec.digest.DigestUtils;
import org.openrdf.query.MalformedQueryException;
import org.openrdf.query.QueryEvaluationException;

import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.object.ObjectConnection;
import org.openrdf.rio.RDFWriter;

import play.data.Form;
import play.mvc.*;

public class UserController extends Controller {

	static String userSessionKey = "user";

	public static Result logout() {
		session().remove(userSessionKey);

		return redirect(routes.ApplicationController.index());
	}

	public static Result submitLoginForm() throws DatatypeConfigurationException, RepositoryException,
			QueryEvaluationException, MalformedQueryException {
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

			session(userSessionKey, user.getId());

			return redirectToMain();
		}
	}

	public static Result submitRegisterForm() throws RepositoryException, DatatypeConfigurationException {
		Form<forms.Register> formRegister = form(forms.Register.class).bindFromRequest();

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
			newUser.setRole(Role.MEMBER);
			newUser.setPasswordSha1Hash(DigestUtils.sha256Hex(form.password));

			String uid = newUser.getLoginName().toLowerCase();
			oc.addObject(models.User.URI + uid, newUser);

			session(userSessionKey, uid);

			return redirectToMain();
		}
	}

	public static boolean isLogged() {
		return getLoggedUser() != null;
	}

	public static models.User getLoggedUser() {
		ObjectConnection oc = Sesame.getObjectConnection();
		String uid = session(userSessionKey);

		if (uid == null) {
			return null;
		}

		try {
			return oc.getObject(models.User.class, models.User.URI + uid);
		} catch (RepositoryException | QueryEvaluationException e) {
			return null;
		}
	}

	public static Result redirectToMain() {
		//return redirect(routes.ManagerController.dashboard());
		models.User user = getLoggedUser();
		return redirect( (user != null && user.hasRights()) ? routes.AdminPanelController.userlist() : routes.ManagerController.dashboard());
	}
	
	public static Result login() {
		if (isLogged()) {
			return redirectToMain();
		}

		Form<Login> formLogin = form(Login.class);
		Form<Register> formRegister = form(Register.class);

		return ok(views.html.global.login.render(formLogin, formRegister));
	}

	public static Result showRDF(String uid, String format) {
		ObjectConnection oc = Sesame.getObjectConnection();
		StringWriter strw = new StringWriter();
		try {
			RDFWriter writer = Sesame.getWriter(strw, format);
			String queryString = "DESCRIBE <" + models.User.URI + uid + ">";
			oc.prepareGraphQuery(QueryLanguage.SPARQL, queryString).evaluate(writer);
		} catch (Exception e) {
			e.printStackTrace();
			return notFound();
		}
		return ok(strw.toString());
	}
}
