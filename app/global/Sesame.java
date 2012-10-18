package global;

import java.io.File;
import java.security.InvalidParameterException;

import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.config.RepositoryConfigException;
import org.openrdf.repository.object.ObjectConnection;
import org.openrdf.repository.object.ObjectRepository;
import org.openrdf.repository.object.config.ObjectRepositoryConfig;
import org.openrdf.repository.object.config.ObjectRepositoryFactory;
import org.openrdf.repository.sail.SailRepository;
import org.openrdf.sail.inferencer.fc.ForwardChainingRDFSInferencer;
import org.openrdf.sail.nativerdf.NativeStore;

public class Sesame {

	/**
	 * La connexion au repertoire d'objets RDF global de l'application
	 */
	private static ObjectConnection connection;

	/**
	 * Initialise la connexion sur le répertoire d'objets RDF
	 * 
	 * @param dir
	 *            Le dossier qui va servir de store pour Sésame
	 * @throws RepositoryException
	 * @throws RepositoryConfigException
	 */
	public static void initialize(String dir) {
		if (null == dir) {
			throw new InvalidParameterException("L'emplacement du répertoire d'enregistrement du store doit être spécifié.");
		}

		// Création d'un repertoire sur disque avec gestion de l'inférence
		Repository r = new SailRepository(new ForwardChainingRDFSInferencer(new NativeStore(new File(dir))));

		ObjectRepositoryFactory factory = new ObjectRepositoryFactory();
		ObjectRepositoryConfig config = factory.getConfig();

		ObjectRepository or = null;
		try {
			or = factory.createRepository(config, r);
			or.initialize();
			Sesame.connection = or.getConnection();
		} catch (RepositoryConfigException | RepositoryException e) {
			e.printStackTrace();
			throw new RuntimeException("Erreur à l'initialisation, redémarrez Play!");
		}
	}

	/**
	 * Ferme proprement la connexion et le répertoire d'objets RDF
	 */
	public static void shutdown() {
		ObjectRepository or = connection.getRepository();
		try {
			connection.close();
			or.shutDown();
		} catch (RepositoryException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	public static ObjectConnection getObjectConnection() {
		return connection;
	}
}
