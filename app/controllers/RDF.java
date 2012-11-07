package controllers;


import global.Sesame;

import java.io.File;
import java.io.StringWriter;
import java.util.Map.Entry;

import org.openrdf.model.Statement;
import org.openrdf.query.GraphQueryResult;
import org.openrdf.query.QueryEvaluationException;
import org.openrdf.repository.object.ObjectConnection;
import org.openrdf.rio.RDFHandlerException;
import org.openrdf.rio.RDFWriter;

import play.mvc.Controller;
import play.mvc.Result;

public class RDF extends Controller {
	
	private static void writeRDF(RDFWriter writer, GraphQueryResult gqr) throws RDFHandlerException, QueryEvaluationException {
		writer.startRDF();
		
		 for (Entry<String, String> entry: gqr.getNamespaces().entrySet()) {
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
	public static Result hunt(String hid, String format) {
//		ObjectConnection oc = Sesame.getObjectConnection();
//		
//		StringWriter strw = new StringWriter();
//		try {
//			GraphQueryResult gqr = oc.getObject(models.Hunt.class, models.Hunt.URI + hid).getGraph();
//			RDFWriter writer = Sesame.getWriter(strw, format);
//			writeRDF(writer, gqr);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return notFound();
//		}
//		
//		return ok(strw.toString());
		return null;
	}
	
	public static Result ontology() {
		 response().setContentType("text/turtle");
		 
		 return ok(new File("./data/ontology.ttl"));
	}
}
