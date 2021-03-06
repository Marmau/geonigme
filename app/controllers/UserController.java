package controllers;

import forms.Login;
import forms.Register;
import global.AssociatedPage;
import global.Sesame;

import java.io.StringWriter;

import models.User;

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
import repository.RoleRepository;
import repository.UserRepository;

public class UserController extends Controller {

	static String userSessionKey = "user";

	@AssociatedPage("logout")
	public static Result logout() {
		session().clear();
		return redirect(routes.ApplicationController.index());
	}

	@AssociatedPage("login")
	public static Result login() {
		if (UserRepository.isLogged()) {
			System.out.println("UserController.login() : Already logged in.");
			return redirectToMain();
		}

		Form<Login> formLogin = form(Login.class);
		Form<Register> formRegister = form(Register.class);

		return ok(views.html.global.login.render(formLogin, formRegister));
	}

	@AssociatedPage("login")
	public static Result submitLoginForm() throws DatatypeConfigurationException, RepositoryException,
			QueryEvaluationException, MalformedQueryException {
		Form<forms.Login> formLogin = form(forms.Login.class).bindFromRequest();

		if (formLogin.hasErrors()) {
			return badRequest(views.html.global.login.render(formLogin, form(Register.class)));
		} else {
			ObjectConnection oc = Sesame.getObjectConnection();
			forms.Login form = formLogin.get();

			String uid = form.login.toLowerCase();
			User user = oc.getObject(User.class, User.URI + uid);

			GregorianCalendar gcal = (GregorianCalendar) GregorianCalendar.getInstance();
			XMLGregorianCalendar now = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);

			user.setLastLoginTime(now);
			oc.addObject(user.getResource(), user);
			session().clear();
			session(userSessionKey, user.getId());
			
			return redirectToMain();
		}
	}

	@AssociatedPage("login")
	public static Result submitRegisterForm() throws RepositoryException, DatatypeConfigurationException {
		Form<forms.Register> formRegister = form(forms.Register.class).bindFromRequest();

		if (formRegister.hasErrors()) {
			return badRequest(views.html.global.login.render(form(Login.class), formRegister));
		} else {
			ObjectConnection oc = Sesame.getObjectConnection();

			forms.Register form = formRegister.get();
			User newUser = new User();
			GregorianCalendar gcal = (GregorianCalendar) GregorianCalendar.getInstance();
			XMLGregorianCalendar now = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
			newUser.setInscriptionDate(now);
			newUser.setLastLoginTime(now);
			newUser.setLoginName(form.pseudonym);
			newUser.setMail(form.email);
			newUser.setRole(RoleRepository.MEMBER);
			newUser.setPasswordSha1Hash(DigestUtils.sha256Hex(form.password));

			String uid = newUser.getLoginName().toLowerCase();
			oc.addObject(User.URI + uid, newUser);

			session(userSessionKey, uid);

			return redirectToMain();
		}
	}

	public static Result redirectToMain() {
		User user = UserRepository.getLoggedUser();
		return redirect((user != null && user.hasRights()) ? routes.AdminPanelController.home()
				: routes.ManagerController.dashboard());
	}

	public static Result showRDF(String uid, String format) {
		ObjectConnection oc = Sesame.getObjectConnection();
		StringWriter strw = new StringWriter();
		try {
			RDFWriter writer = Sesame.getWriter(strw, format);
			String queryString = "DESCRIBE <" + User.URI + uid + ">";
			oc.prepareGraphQuery(QueryLanguage.SPARQL, queryString).evaluate(writer);
		} catch (Exception e) {
			e.printStackTrace();
			return notFound();
		}
		return ok(strw.toString());
	}
}
