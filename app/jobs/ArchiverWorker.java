package jobs;

import global.Sesame;

import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map.Entry;

import org.openrdf.model.Statement;
import org.openrdf.query.GraphQueryResult;
import org.openrdf.query.QueryEvaluationException;
import org.openrdf.repository.object.ObjectConnection;
import org.openrdf.rio.RDFHandlerException;
import org.openrdf.rio.RDFWriter;

import play.Play;

import akka.actor.UntypedActor;

public class ArchiverWorker extends UntypedActor {

	@Override
	public void onReceive(Object message) throws Exception {
		archive();
		
		// Prévoir : supprimer vieilles sauvegarde, compresser...
	}

	private static void writeRDF(RDFWriter writer, GraphQueryResult gqr) throws RDFHandlerException,
			QueryEvaluationException {
		writer.startRDF();

		for (Entry<String, String> entry : gqr.getNamespaces().entrySet()) {
			String prefix = entry.getKey();
			String namespace = entry.getValue();
			writer.handleNamespace(prefix, namespace);
		}

		while (gqr.hasNext()) {
			Statement st = gqr.next();
			writer.handleStatement(st);
		}

		writer.endRDF();
	}

	private void archive() {
		ObjectConnection oc = Sesame.getObjectConnection();

		StringWriter strw = new StringWriter();
		try {
			GraphQueryResult gqr = oc.prepareGraphQuery("CONSTRUCT { ?s ?p ?o } WHERE { ?s ?p ?o }").evaluate();
			RDFWriter writer = Sesame.getWriter(strw, "turtle");
			writeRDF(writer, gqr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.mm.dd.HHmmss");
		
		String saveDir = Play.application().configuration().getString("save.triplets.directory");
		if (saveDir == null) {
			System.out.println("Pas de dossier de sauvegarde défini dans application.conf : abandon");
			return;
		}
		
		String filename = "triplets-" + sdf.format(now) + ".ttl";
		
		FileWriter fw;
		try {
			fw = new FileWriter(Play.application().getFile(saveDir + "/" + filename));
			fw.write(strw.toString());
			fw.close();
		} catch (IOException e) {
			System.out.println("Dossier n'existe pas : abandon");
		}
	}

}
