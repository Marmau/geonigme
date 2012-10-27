package controllers;


import java.io.File;
import java.util.LinkedHashMap;

import javax.xml.parsers.*;

import org.w3c.dom.*;


import play.api.libs.json.JsValue;
import play.mvc.*;

public class Map extends Controller{

	public static Result getMapArbres() {
		String path = "./data/arbres.rdf";
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
	
	public static Result getMapLieuxPublic() {
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
	
	
	public static Result getMapFontaines() {
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
