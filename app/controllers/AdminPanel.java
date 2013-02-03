package controllers;

import javax.xml.datatype.DatatypeConfigurationException;

import models.Role;

import org.openrdf.query.QueryEvaluationException;
import org.openrdf.repository.RepositoryException;

import forms.AdmUserEdit;

import play.data.Form;
import play.mvc.*;

public class AdminPanel extends Controller {

	public static Result userlist() throws RepositoryException, QueryEvaluationException {
		if( !UserController.isLogged() ) {
			return redirect(routes.ApplicationController.index());
		}
		return ok(views.html.adminpanel.userlist.render(models.User.getAll()));
	}

	public static Result useredit(String uid) throws RepositoryException, QueryEvaluationException {
		if( !UserController.isLogged() ) {
			return redirect(routes.ApplicationController.index());
		}
		models.User user = models.User.get(uid);
		if( user == null ) {
			return notFound();
		}
		//Form<AdmUserEdit> formUserEdit = form(AdmUserEdit.class);
		forms.AdmUserEdit formUserEdit = new forms.AdmUserEdit();
		//System.out.println(formUserEdit);
		formUserEdit.roleName = user.getRole().getName();
		return ok(views.html.adminpanel.editUser.render(user, form(forms.AdmUserEdit.class).fill(formUserEdit)));
	}
	
	public static Result submitUserEditForm(String uid) throws RepositoryException, DatatypeConfigurationException {
		if( !UserController.isLogged() ) {
			return redirect(routes.ApplicationController.index());
		}
		models.User user = models.User.get(uid);
		if( user == null ) {
			return notFound();
		}
		
		Form<forms.AdmUserEdit> formUserEdit= form(forms.AdmUserEdit.class).bindFromRequest();
		
		if( formUserEdit.hasErrors() ) {
			return badRequest(views.html.adminpanel.editUser.render(user, formUserEdit));
			
		} else {
			forms.AdmUserEdit form = formUserEdit.get();
			
			user.setRole(Role.get(form.roleName));
			user.save();
			session(UserController.userSessionKey, uid);
			
			return redirectToMain();
		}
	}

	public static Result redirectToMain() {
		return redirect(routes.AdminPanel.userlist());
	}
}
