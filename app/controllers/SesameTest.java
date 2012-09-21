package controllers;

import java.io.File;

import org.openrdf.OpenRDFException;
import org.openrdf.model.Literal;
import org.openrdf.model.URI;
import org.openrdf.model.Value;
import org.openrdf.model.ValueFactory;
import org.openrdf.model.vocabulary.RDF;
import org.openrdf.query.BindingSet;
import org.openrdf.query.QueryLanguage;
import org.openrdf.query.TupleQuery;
import org.openrdf.query.TupleQueryResult;
import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.sail.SailRepository;
import org.openrdf.sail.inferencer.fc.ForwardChainingRDFSInferencer;
import org.openrdf.sail.nativerdf.NativeStore;

import play.mvc.Controller;
import play.mvc.Result;

public class SesameTest extends Controller {
    public static Result sesameWrite() {
	// Sésame
	File dataDir = new File("test-store/");

	Repository myRepository = new SailRepository(
		new ForwardChainingRDFSInferencer(new NativeStore(dataDir)));

	try {
	    myRepository.initialize();
	} catch (RepositoryException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	ValueFactory f = myRepository.getValueFactory();

	if (f == null)
	    System.out.println("lol");

	System.out.println("SALUT");
	URI alice = f.createURI("http://example.org/people/alice");
	URI bob = f.createURI("http://example.org/people/bob");
	URI name = f.createURI("http://example.org/ontology/name");
	URI person = f.createURI("http://example.org/ontology/Person");

	Literal alicesName = f.createLiteral("Alice");
	Literal bobsName = f.createLiteral("Bob");

	try {
	    RepositoryConnection con = myRepository.getConnection();
	    try {
		// bob is a person
		con.add(bob, RDF.TYPE, person);
		// bob's name is "Bob"
		con.add(bob, name, bobsName);

		con.add(alice, RDF.TYPE, person);
		con.add(alice, name, alicesName);
	    } finally {
		con.close();
		myRepository.shutDown();
		System.out.println(myRepository.isInitialized());
	    }
	} catch (OpenRDFException e) {
	    // handle exception
	}

	return ok();
    }

    public static Result sesameRead() {
	// Sésame
	File dataDir = new File("test-store/");

	SailRepository myRepository = new SailRepository(
		new ForwardChainingRDFSInferencer(new NativeStore(dataDir)));

	try {
	    myRepository.initialize();
	    System.out.println(myRepository.isInitialized());
	} catch (RepositoryException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	try {
	    RepositoryConnection con = myRepository.getConnection();
	    try {
		String queryString = "SELECT * WHERE { <http://example.org/people/alice> ?p ?o }";
		TupleQuery tupleQuery = con.prepareTupleQuery(
			QueryLanguage.SPARQL, queryString);
		TupleQueryResult result = tupleQuery.evaluate();

		while (result.hasNext()) {
		    BindingSet bindingSet = result.next();
		    Value valueOfX = bindingSet.getValue("p");
		    Value valueOfY = bindingSet.getValue("o");

		    System.out.println(valueOfX);
		    System.out.println(valueOfY);
		    System.out.println("___");
		}
	    } finally {
		con.close();
		myRepository.shutDown();
	    }
	} catch (OpenRDFException e) {
	    // handle exception
	    e.printStackTrace();
	}

	return ok();
    }
}
