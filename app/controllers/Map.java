package controllers;


import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import javax.xml.parsers.*;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.w3c.dom.*;

import play.libs.F.Promise;
import play.libs.WS;
import play.libs.WS.Response;
import play.mvc.*;

public class Map extends Controller{
	
	public static Result routing() throws MalformedURLException, IOException {	
		Promise<Response> result = WS.url("http://boussole.mandarine34.fr/api/7c2xrVH6AhnOqpKd179ioUPxar8IEcvqdtSFD0sJ/getRouting/")
			.setQueryParameter("domain", "boussole.mandarine34.fr")
			.setQueryParameter("start", request().queryString().get("start")[0])
			.setQueryParameter("end", request().queryString().get("target")[0])
			.get();
		
		
		return ok(result.get((long) 1000000).asJson());
	}
	
	public static JsonNode rdfXmlToGeoJson(File file) {
		Element rootRdf = null;
		HashMap<String, Object> geoJSONResult = null;
		try {
			rootRdf =  DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file).getDocumentElement();			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (rootRdf == null) {
			throw new RuntimeException();
		}
		
		geoJSONResult = new HashMap<String, Object>();
		List<Object> contentTab = new ArrayList<Object>();			
		geoJSONResult.put("type", "FeatureCollection");
		NodeList nodeRdf = rootRdf.getElementsByTagName("rdf:Description");
		
		for (int i = 0; i < nodeRdf.getLength() ; ++i) {
			LinkedHashMap<Object, Object> geoFeatureDescriptionMap = new LinkedHashMap<Object, Object>();
			String coordList[] = new String[2];
			LinkedHashMap<Object, Object> propertiesMap = new LinkedHashMap<Object, Object>();
			LinkedHashMap<Object, Object> featuresMap = new LinkedHashMap<Object, Object>();
			
			// Meta données					
			featuresMap.put("type", "Feature");
			featuresMap.put("id", i);
			
			// Données géo 
			geoFeatureDescriptionMap.put("type", "Point");
			NodeList coordsRdf = nodeRdf.item(i).getChildNodes();
			for (int j = 0 ; j < coordsRdf.getLength() ; ++j) {
				if (coordsRdf.item(j).getNodeName() == "geo:long" || coordsRdf.item(j).getNodeName() == "prop-fr:longitude") {
					coordList[0] = coordsRdf.item(j).getTextContent();
				} else if (coordsRdf.item(j).getNodeName() == "geo:lat"  || coordsRdf.item(j).getNodeName() == "prop-fr:latitude") {
					coordList[1] = coordsRdf.item(j).getTextContent();
				} else if (coordsRdf.item(j).getNodeName() != "#text"){
					// Propriétés
					if (coordsRdf.item(j).getTextContent().length() > 0) {
						propertiesMap.put(coordsRdf.item(j).getNodeName(), coordsRdf.item(j).getTextContent());
					} else {
						propertiesMap.put(coordsRdf.item(j).getNodeName(), ((Element)coordsRdf.item(j)).getAttribute("rdf:resource"));
					}
				}
			}
			
			geoFeatureDescriptionMap.put("coordinates", coordList);
			featuresMap.put("geometry", geoFeatureDescriptionMap);
			featuresMap.put("properties", propertiesMap);
			contentTab.add(featuresMap);	
			geoJSONResult.put("features", contentTab);
		}
		
		return new ObjectMapper().valueToTree(geoJSONResult);
	}
	
	public static Result getTreesMontpellierJSON() {
		String path = "./data/arbresinter.rdf";
		
		return ok(rdfXmlToGeoJson(new File(path)));
	}
	
	public static Result getGardensMontpellierJSON() {
		String path = "./data/jardins.rdf";
		
		return ok(rdfXmlToGeoJson(new File(path)));
	}

	public static Result getPublicPlacesMontpellierJSON() {
		String path = "./data/lieuxPublics.rdf";

		return ok(rdfXmlToGeoJson(new File(path)));
	}
	
	
	public static Result getFoutainsMontpellierJSON() {
		String path = "./data/fontaines.rdf";

		return ok(rdfXmlToGeoJson(new File(path)));
	}
	
	public static Result getGreenSpacesMontpellierJSON() {
		String path = "./data/espacesVerts.rdf";

		return ok(rdfXmlToGeoJson(new File(path)));
	}
	
	public static Result getMonumentsMontpellierJSON() {
		String path = "./data/dbpedia-monuments.rdf";

		return ok(rdfXmlToGeoJson(new File(path)));
	}
	
	
}
