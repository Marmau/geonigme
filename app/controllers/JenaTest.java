package controllers;

import com.hp.hpl.jena.query.Dataset;
import com.hp.hpl.jena.rdf.model.*;
import com.hp.hpl.jena.tdb.TDBFactory;

import play.mvc.*;

public class JenaTest extends Controller {
    public static Result jenaRead() {
	Dataset d = TDBFactory.createDataset("dataset-test");

	Model m = ModelFactory.createDefaultModel();
	m.createResource("//test1");
	m.getResource("//test1").addProperty(m.createProperty("//property1"),
		"lol");

	d.addNamedModel("//modeltest2", m);

	System.out.println("____________________");
	m.write(System.out, "Turtle");

	m.close();
	d.close();

	return ok();
    }

    public static Result jenaWrite() {
	Dataset d = TDBFactory.createDataset("dataset-test");

	Model m = d.getNamedModel("//modeltest2");

	System.out.println("______");
	m.write(System.out, "Turtle");

	return ok();
    }
}
