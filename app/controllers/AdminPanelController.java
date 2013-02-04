package controllers;

import java.util.Iterator;

import global.CurrentRequest;
import global.Page;

import models.Hunt;
import models.Role;
import models.User;

import play.data.Form;
import play.mvc.*;
import repository.HuntRepository;
import repository.UserRepository;

public class AdminPanelController extends Controller {
	
	public static Page currentPage = null;
	
	/***** HUNTS *****/

	public static Result huntlist() throws Exception {
		currentPage = Page.get("huntlist");
		if( !currentPage.userCanAccess() ) {
			System.out.println("AdminPanelController.huntlist() : Access forbidden.");
			return forbidden();
		}
		return ok(views.html.adminpanel.huntlist.render(HuntRepository.getAll()));
	}

	public static Result huntedit(String uid) throws Exception {
		currentPage = Page.get("huntedit");
		if( !currentPage.userCanAccess() ) {
			return forbidden();
		}
		Hunt hunt = HuntRepository.get(uid);
		if( hunt == null ) {
			return notFound();
		}
		
		forms.Hunt formHuntEdit = new forms.Hunt();
		
		formHuntEdit.label = hunt.getLabel();
		formHuntEdit.description = hunt.getDescription();
		formHuntEdit.level = hunt.getLevel();
		formHuntEdit.area = hunt.getArea().toTemplateString();
		
		Iterator<models.Tag> it = hunt.getTags().iterator();
		if (it.hasNext()) {
			models.Tag firstTag = it.next();
			formHuntEdit.tags = firstTag.getName();
			
			while (it.hasNext()) {
				formHuntEdit.tags += ", " + it.next().getName();
			}
		}
		return ok(views.html.adminpanel.editHunt.render(hunt, form(forms.Hunt.class).fill(formHuntEdit)));
	}
	
	public static Result submitHuntEditForm(String uid) throws Exception {
		currentPage = Page.get("huntedit");
		if( !currentPage.userCanAccess() ) {
			return forbidden();
		}
		Hunt hunt = HuntRepository.get(uid);
		if( hunt == null ) {
			return notFound();
		}

		Form<forms.Hunt> formHuntEdit= form(forms.Hunt.class).bindFromRequest();
		
		if( formHuntEdit.hasErrors() ) {
			return badRequest(views.html.adminpanel.editHunt.render(hunt, formHuntEdit));
			
		} else {
			HuntController.fillHunt(hunt, formHuntEdit.get());
			//forms.AdmHuntEdit form = formHuntEdit.get();
			
			hunt.save();
			
			return redirectToMain();
		}
	}
	
	/***** USERS *****/
	
	public static Result userlist() throws Exception {
		System.out.println("AdminPanelController Current thread: "+Thread.currentThread().getId());
		System.out.println("AdminPanelController Current request: "+ctx().request().hashCode());
		CurrentRequest.test();
		currentPage = Page.get("userlist");
		if( !currentPage.userCanAccess() ) {
			System.out.println("AdminPanelController.userlist() : Access forbidden.");
			return forbidden();
		}
		return ok(views.html.adminpanel.userlist.render(UserRepository.getAll()));
	}

	public static Result useredit(String uid) throws Exception {
		currentPage = Page.get("useredit");
		if( !currentPage.userCanAccess() ) {
			return forbidden();
		}
		User user = UserRepository.get(uid);
		if( user == null ) {
			return notFound();
		}
		//Form<AdmUserEdit> formUserEdit = form(AdmUserEdit.class);
		forms.AdmUserEdit formUserEdit = new forms.AdmUserEdit();
		//System.out.println(formUserEdit);
		formUserEdit.roleName = user.getRole().getName();
		return ok(views.html.adminpanel.editUser.render(user, form(forms.AdmUserEdit.class).fill(formUserEdit)));
	}
	
	public static Result submitUserEditForm(String uid) throws Exception {
		currentPage = Page.get("useredit");
		if( !currentPage.userCanAccess() ) {
			return forbidden();
		}
		User user = UserRepository.get(uid);
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
		return redirect(routes.AdminPanelController.userlist());
	}
}
