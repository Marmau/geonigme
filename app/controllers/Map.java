package controllers;


import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import javax.xml.parsers.*;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.w3c.dom.*;


import play.api.libs.json.JsValue;
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
		
		
		return ok(result.get().asJson());
	}
	
	public static Result getTreesMontpellierJSON() throws JsonGenerationException, JsonMappingException, IOException {
		String path = "./data/arbres.rdf";
		Element rootRdf = null;
		HashMap<String, Object> geoJSONResult = null;
		try {
			rootRdf =  DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File(path)).getDocumentElement();			
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
			List<Object> coordList = new ArrayList<Object>();
			LinkedHashMap<Object, Object> propertiesMap = new LinkedHashMap<Object, Object>();
			LinkedHashMap<Object, Object> featuresMap = new LinkedHashMap<Object, Object>();
			
			// Meta données					
			featuresMap.put("type", "Feature");
			featuresMap.put("id", i);
			
			// Données géo 
			geoFeatureDescriptionMap.put("type", "Point");
			NodeList coordsRdf = nodeRdf.item(i).getChildNodes();
			for (int j = 0 ; j < coordsRdf.getLength() ; ++j) {
				if (coordsRdf.item(j).getNodeName() == "geo:long") {
					coordList.add(coordsRdf.item(j).getTextContent());
				} else if (coordsRdf.item(j).getNodeName() == "geo:lat") {
					coordList.add(coordsRdf.item(j).getTextContent());
				} else if (coordsRdf.item(j).getNodeName() != "#text"){
					// Propriétés
					propertiesMap.put(coordsRdf.item(j).getNodeName(), coordsRdf.item(j).getTextContent());
				}
			}
			
			geoFeatureDescriptionMap.put("coordinates", coordList);
			featuresMap.put("geometry", geoFeatureDescriptionMap);
			featuresMap.put("properties", propertiesMap);
			contentTab.add(featuresMap);	
			geoJSONResult.put("features", contentTab);
		}
		
		ObjectMapper mapper = new ObjectMapper();

		return ok(mapper.valueToTree(geoJSONResult));
	}

	public static Result getPublicPlacesMontpellierJSON() {
		String path = "./data/lieuxPublics.rdf";
		File xml = new File(path);
		Element racine = null;
		LinkedHashMap<Object, Object> geoJSONTab = null;
		try {
			DocumentBuilderFactory fabrique = DocumentBuilderFactory.newInstance();
			DocumentBuilder constructeur = fabrique.newDocumentBuilder();
			racine = constructeur.parse(xml).getDocumentElement();			
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (racine != null) {
			geoJSONTab = new LinkedHashMap<Object, Object>();
			LinkedHashMap<Object, Object> contentTab = new LinkedHashMap<Object, Object>();			
			geoJSONTab.put("type", "FeatureCollection");
			NodeList nodeList = racine.getElementsByTagName("rdf:Description");
			for (int i = 0; i < nodeList.getLength() ; ++i) {
				LinkedHashMap<Object, Object> geoFeatureDescriptionTab = new LinkedHashMap<Object, Object>();
				LinkedHashMap<Object, Object> coordTab = new LinkedHashMap<Object, Object>();
				LinkedHashMap<Object, Object> propTab = new LinkedHashMap<Object, Object>();
				LinkedHashMap<Object, Object> featuresTab = new LinkedHashMap<Object, Object>();
				
				// Meta données					
				featuresTab.put("type", "Feature");
				featuresTab.put("id", "id" + i);
				
				// Données géo 
				geoFeatureDescriptionTab.put("type", "Point");
				NodeList coordsList = nodeList.item(i).getChildNodes();
				for (int j = 0 ; j < coordsList.getLength() ; ++j) {
					if (coordsList.item(j).getNodeName() == "geo:long") {
						coordTab.put(1, coordsList.item(j).getTextContent());
					} else if (coordsList.item(j).getNodeName() == "geo:lat") {
						coordTab.put(2, coordsList.item(j).getTextContent());
					} else if (coordsList.item(j).getNodeName() != "#text"){
				// Propriétés
						propTab.put(coordsList.item(j).getNodeName(), coordsList.item(j).getTextContent().replace("\"", "'"));
					}
				}
				
				geoFeatureDescriptionTab.put("coordinates", coordTab);
				featuresTab.put("geometry", geoFeatureDescriptionTab);
				featuresTab.put("properties", propTab);
				contentTab.put(i, featuresTab);	
				geoJSONTab.put("features", contentTab);
			}
		}
		JsValue json = play.api.libs.json.Json.parse("\"" + geoJSONTab.toString() + "\"");
		
		return ok(json.toString());
	}
	
	
	public static Result getFoutainsMontpellierJSON() {
		String path = "./data/fontaine.rdf";
		File xml = new File(path);
		Element racine = null;
		LinkedHashMap<Object, Object> geoJSONTab = null;
		try {
			DocumentBuilderFactory fabrique = DocumentBuilderFactory.newInstance();
			DocumentBuilder constructeur = fabrique.newDocumentBuilder();
			racine = constructeur.parse(xml).getDocumentElement();			
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (racine != null) {
			geoJSONTab = new LinkedHashMap<Object, Object>();
			LinkedHashMap<Object, Object> contentTab = new LinkedHashMap<Object, Object>();			
			geoJSONTab.put("type", "FeatureCollection");
			NodeList nodeList = racine.getElementsByTagName("rdf:Description");
			for (int i = 0; i < nodeList.getLength() ; ++i) {
				LinkedHashMap<Object, Object> geoFeatureDescriptionTab = new LinkedHashMap<Object, Object>();
				LinkedHashMap<Object, Object> coordTab = new LinkedHashMap<Object, Object>();
				LinkedHashMap<Object, Object> propTab = new LinkedHashMap<Object, Object>();
				LinkedHashMap<Object, Object> featuresTab = new LinkedHashMap<Object, Object>();
				
				// Meta données					
				featuresTab.put("type", "Feature");
				featuresTab.put("id", "id" + i);
				
				// Données géo 
				geoFeatureDescriptionTab.put("type", "Point");
				NodeList coordsList = nodeList.item(i).getChildNodes();
				for (int j = 0 ; j < coordsList.getLength() ; ++j) {
					if (coordsList.item(j).getNodeName() == "geo:long") {
						coordTab.put(1, coordsList.item(j).getTextContent());
					} else if (coordsList.item(j).getNodeName() == "geo:lat") {
						coordTab.put(2, coordsList.item(j).getTextContent());
					} else if (coordsList.item(j).getNodeName() != "#text"){
				// Propriétés
						propTab.put(coordsList.item(j).getNodeName(), coordsList.item(j).getTextContent().replace("\"", "'"));
					}
				}
				
				geoFeatureDescriptionTab.put("coordinates", coordTab);
				featuresTab.put("geometry", geoFeatureDescriptionTab);
				featuresTab.put("properties", propTab);
				contentTab.put(i, featuresTab);	
				geoJSONTab.put("features", contentTab);
			}
		}
		JsValue json = play.api.libs.json.Json.parse("\"" + geoJSONTab.toString() + "\"");
		
		return ok(json.toString());
	}
	
	
}
