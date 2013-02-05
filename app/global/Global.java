package global;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import models.Right;

import controllers.routes;

import pages.AdmHuntEditPage;
import pages.AdminPanelPage;
import pages.AdminUserEditPage;
import pages.DashboardPage;
import pages.GamePage;
import pages.HomePage;
import play.Application;
import play.GlobalSettings;
import play.api.mvc.Call;
import play.api.mvc.Handler;
import play.mvc.Action;
import play.mvc.Http.Request;
import play.mvc.Http.RequestHeader;

public class Global extends GlobalSettings {

	@Override
	public void onStart(Application app) {
		super.onStart(app);

		// Initialisation de Sésame
		String sesameDir = app.configuration().getString("sesame.store.directory");

		if (sesameDir != null) {
			Sesame.initialize(sesameDir);
		} else {
			throw new RuntimeException("sesame.store.directory de application.conf doit être spécifié");
		}

		// System.out.println("URL: "+getURLFromRoute("routes.AdminPanelController.userlist"));

		initializeFormatters();

		// Pages
		try {

			// Admin Panel Pages
			new AdminPanelPage("userlist", "Utilisateurs", routes.AdminPanelController.userlist(), Right.USER_LIST, "");
			new AdminPanelPage("huntlist", "Chasses", routes.AdminPanelController.huntlist(), Right.HUNT_LIST, "");
			new AdminUserEditPage("Édition d'un utilisateur", Right.USER_LIST, "");
			new AdmHuntEditPage("Édition d'une chasse", Right.HUNT_LIST, "dashboard/hunt");
			
			// Dashboard Pages
			new DashboardPage("dashboard", "Tableau de bord", routes.ManagerController.dashboard(), Right.MEMBER_AREA, "");
			
			// Game Pages
			new GamePage("play", "Jouer", routes.GameController.home(), Right.MEMBER_AREA, "");

			// Home Pages
			new HomePage("logout", "Déconnexion", routes.UserController.logout(), Right.MEMBER_AREA, "");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
 	
	@Override
 	public Action<?> onRequest(Request request, Method actionMethod) {
 		
 		//System.out.println("Requesting");
 		Action<?> action = super.onRequest(request, actionMethod);
 		//System.out.println("Request done");
 		return action;
 	}
 	
 	@Override
 	public Handler onRouteRequest(RequestHeader arg0) {
 		
 		//System.out.println("onRouteRequest");
 		Handler handler = super.onRouteRequest(arg0);
 		//System.out.println("onRouteRequest done");
 		return handler;
 	}

	@Override
	public void onStop(Application app) {
		// Extinction de Sésame
		Sesame.shutdown();

		super.onStop(app);
	}

	private void initializeFormatters() {

	}

	// Don't care about parameters
	public static String getURLFromRoute(String route) {
		try {
			String[] subs = route.split("\\.");
			for (Field f : routes.class.getFields()) {
				if (f.getName().equals(subs[1])) {
					Object ctrl = f.getType().newInstance();
					for (Method m : f.getType().getMethods()) {
						if (m.getName().equals(subs[2])) {
							Call c = (Call) m.invoke(ctrl);
							
							return c.url();
						}
					}
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
