package global;

import play.Application;
import play.GlobalSettings;

public class Global extends GlobalSettings {

    @Override
    public void onStart(Application app) {
	super.onStart(app);

	// Initialisation de Sésame
	String sesameDir = app.configuration().getString(
		"sesame.store.directory");

	if (null != sesameDir) {
	    Sesame.initialize(sesameDir);
	} else {
	    throw new RuntimeException(
		    "sesame.store.directory de application.conf doit être spécifié");
	}
    }

    @Override
    public void onStop(Application app) {
	super.onStop(app);

	// Initialisation de Sésame
	Sesame.shutdown();
    }
}
