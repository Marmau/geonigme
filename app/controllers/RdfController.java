package controllers;

import global.Sesame;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.Map.Entry;

import models.NS;

import org.openrdf.model.Statement;
import org.openrdf.query.GraphQueryResult;
import org.openrdf.query.MalformedQueryException;
import org.openrdf.query.QueryEvaluationException;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.object.ObjectConnection;
import org.openrdf.rio.RDFFormat;
import org.openrdf.rio.RDFHandlerException;
import org.openrdf.rio.RDFParseException;
import org.openrdf.rio.RDFWriter;

import play.Play;
import play.mvc.Controller;
import play.mvc.Result;

public class RdfController extends Controller {

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
	
	public static Result restore() throws RDFParseException, RDFHandlerException, IOException, RepositoryException {
		ObjectConnection oc = Sesame.getObjectConnection();
		InputStream is = Play.application().resourceAsStream("public/rdf/geonigme.ttl");
		
		oc.add(is, NS.GNGM, RDFFormat.TURTLE);
		
		return ok("Done");
	}
	

	public static Result reset() throws RepositoryException, QueryEvaluationException {
		ObjectConnection oc = Sesame.getObjectConnection();
		oc.clear();

		return ok();
	}

	public static Result dump() throws QueryEvaluationException, MalformedQueryException, RepositoryException {
		ObjectConnection oc = Sesame.getObjectConnection();

		StringWriter strw = new StringWriter();
		try {
			GraphQueryResult gqr = oc.prepareGraphQuery("CONSTRUCT { ?s ?p ?o } WHERE { ?s ?p ?o }").evaluate();
			RDFWriter writer = Sesame.getWriter(strw, "turtle");
			writeRDF(writer, gqr);
		} catch (Exception e) {
			e.printStackTrace();
			return notFound();
		}
		
		response().setContentType("text/turtle");
		response().setHeader("Content-Disposition", "attachment; filename=geonigme.ttl");
		
		return ok(strw.toString());
	}

	public static Result ontology() {
		response().setContentType("text/turtle");

		return ok(Play.application().resourceAsStream("public/rdf/ontology.ttl"));
	}
}
