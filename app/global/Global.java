package global;

import play.Application;
import play.GlobalSettings;
public class Global extends GlobalSettings {

	@Override
	public void onStart(Application app) {
		super.onStart(app);

		// Initialisation de Sésame
		String sesameDir = app.configuration().getString("sesame.store.directory");

		if (null != sesameDir) {
			Sesame.initialize(sesameDir);
		} else {
			throw new RuntimeException("sesame.store.directory de application.conf doit être spécifié");
		}

		initializeFormatters();
		
	}

	@Override
	public void onStop(Application app) {		
		// Extinction de Sésame
		Sesame.shutdown();
		
		super.onStop(app);
	}

	private void initializeFormatters() {

	}
}
