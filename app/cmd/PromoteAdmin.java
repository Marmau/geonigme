package cmd;

import java.io.File;

import models.User;

import org.openrdf.query.QueryEvaluationException;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.object.ObjectConnection;

import play.Application;
import play.api.Mode;

import repository.RoleRepository;

import global.Sesame;

public class PromoteAdmin {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("cmd <pseudo>");
		}
		
		Application app = new Application(new play.api.Application(new File("."), PromoteAdmin.class.getClassLoader(), null, Mode.Dev()));
		
		// Initialisation de Sésame
		String sesameDir = app.configuration().getString("sesame.store.directory");

		if (sesameDir != null) {
			Sesame.initialize(sesameDir);
		} else {
			throw new RuntimeException("sesame.store.directory de application.conf doit être spécifié");
		}
		
        ObjectConnection oc = Sesame.getObjectConnection();
		
		String login = args[0];
		
		String uid = login.toLowerCase();
		try {
			User user = oc.getObject(User.class, User.URI + uid);
			user.setRole(RoleRepository.ADMINISTRATOR);
			oc.addObject(user.getResource(), user);
		} catch (RepositoryException | QueryEvaluationException e) {
			System.out.println("Fail: unknown user.");
		}
		
		Sesame.shutdown();
		System.out.println("Ok.");
	}

}
