package controllers;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import javax.xml.parsers.*;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.w3c.dom.*;

import play.Play;
import play.libs.F.Promise;
import play.libs.WS;
import play.libs.WS.Response;
import play.mvc.*;

public class MapController extends Controller {

	public static Result routing() throws MalformedURLException, IOException {
		Promise<Response> result = WS
				.url("http://boussole.mandarine34.fr/api/7c2xrVH6AhnOqpKd179ioUPxar8IEcvqdtSFD0sJ/getRouting/")
				.setQueryParameter("domain", "boussole.mandarine34.fr")
				.setQueryParameter("start", request().queryString().get("start")[0])
				.setQueryParameter("end", request().queryString().get("target")[0]).get();

		return ok(result.get((long) 1000000).asJson());
	}

	@SuppressWarnings("unchecked")
	public static JsonNode rdfXmlToGeoJson(InputStream stream) {
		Element rootRdf = null;
		HashMap<String, Object> geoJSONResult = null;
		try {
			rootRdf = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(stream).getDocumentElement();
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

		for (int i = 0; i < nodeRdf.getLength(); ++i) {
			LinkedHashMap<Object, Object> geoFeatureDescriptionMap = new LinkedHashMap<Object, Object>();
			String coordList[] = new String[2];
			LinkedHashMap<Object, Object> propertiesMap = new LinkedHashMap<Object, Object>();
			LinkedHashMap<Object, Object> featuresMap = new LinkedHashMap<Object, Object>();

			// Meta données
			featuresMap.put("type", "Feature");
			featuresMap.put("id", i);

			// Données géo
			geoFeatureDescriptionMap.put("type", "Point");
			NodeList subjectsRdf = nodeRdf.item(i).getChildNodes();
			for (int j = 0; j < subjectsRdf.getLength(); ++j) {
				if (subjectsRdf.item(j).getNodeName() == "geo:long"
						|| subjectsRdf.item(j).getNodeName() == "prop-fr:longitude") {
					coordList[0] = subjectsRdf.item(j).getTextContent();
				} else if (subjectsRdf.item(j).getNodeName() == "geo:lat"
						|| subjectsRdf.item(j).getNodeName() == "prop-fr:latitude") {
					coordList[1] = subjectsRdf.item(j).getTextContent();
				} else if (subjectsRdf.item(j).getNodeName() != "#text") {
					// Propriétés
					if (subjectsRdf.item(j).getTextContent().length() > 0) {
						String key = subjectsRdf.item(j).getNodeName();
						if (propertiesMap.containsKey(key)) {
							Object o = propertiesMap.get(key);
							if (o instanceof List<?>) {
								((List<Object>) o).add(subjectsRdf.item(j).getTextContent());
							} else {
								List<Object> list = new ArrayList<Object>();
								list.add(o);
								list.add(subjectsRdf.item(j).getTextContent());
								propertiesMap.put(key, list);
							}
						} else {
							propertiesMap.put(subjectsRdf.item(j).getNodeName(), subjectsRdf.item(j).getTextContent());
						}
					} else {
						propertiesMap.put(subjectsRdf.item(j).getNodeName(),
								((Element) subjectsRdf.item(j)).getAttribute("rdf:resource"));
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
		String path = "public/rdf/arbresinter.rdf";

		return ok(rdfXmlToGeoJson(Play.application().resourceAsStream(path)));
	}

	public static Result getGardensMontpellierJSON() {
		String path = "public/rdf/jardins.rdf";

		return ok(rdfXmlToGeoJson(Play.application().resourceAsStream(path)));
	}

	public static Result getPublicPlacesMontpellierJSON() {
		String path = "public/rdf/lieuxPublics.rdf";

		return ok(rdfXmlToGeoJson(Play.application().resourceAsStream(path)));
	}

	public static Result getFoutainsMontpellierJSON() {
		String path = "public/rdf/fontaines.rdf";

		return ok(rdfXmlToGeoJson(Play.application().resourceAsStream(path)));
	}

	public static Result getGreenSpacesMontpellierJSON() {
		String path = "public/rdf/espacesVerts.rdf";

		return ok(rdfXmlToGeoJson(Play.application().resourceAsStream(path)));
	}

	public static Result getMonumentsMontpellierJSON() {
		String path = "public/rdf/dbpedia-monuments.rdf";

		return ok(rdfXmlToGeoJson(Play.application().resourceAsStream(path)));
	}

}
