package cmd;

import global.Sesame;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import models.NS;

import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.object.ObjectConnection;
import org.openrdf.rio.RDFFormat;
import org.openrdf.rio.RDFParseException;

import play.Application;
import play.api.Mode;

public class Restore {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("cmd <file.ttl>");
		}
		
		Application app = new Application(new play.api.Application(new File("."), Restore.class.getClassLoader(), null, Mode.Dev()));
		
		// Initialisation de Sésame
		String sesameDir = app.configuration().getString("sesame.store.directory");

		if (sesameDir != null) {
			Sesame.initialize(sesameDir);
		} else {
			throw new RuntimeException("sesame.store.directory de application.conf doit être spécifié");
		}
		
		ObjectConnection oc = Sesame.getObjectConnection();
		
		try {
			InputStream is = new FileInputStream(args[0]);
			
			// Vide la base
			oc.clear();

			// Restaure depuis le flux
			oc.add(is, NS.GNGM, RDFFormat.TURTLE);
			
			is.close();
			System.out.println("Ok.");
		} catch (RepositoryException | RDFParseException | IOException e) {
			System.out.println("Fail: " + e.getMessage() + ".");
		}
		
		Sesame.shutdown();
	}

}
