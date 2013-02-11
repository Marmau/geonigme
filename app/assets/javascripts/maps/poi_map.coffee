define ['maps/base_map', 'maps/icons', 'internationalization', 'leaflet'], (BaseMap, Icons, Translation) ->
	class PoiMap extends BaseMap
		constructor: (element) ->
			super element

			@layersControls = new L.Control.Layers()
			@addControl(@layersControls)

			@monumentsOverlay = new L.LayerGroup()
			@layersControls.addOverlay(@monumentsOverlay, Translation.get("monuments"))

			@treesOverlay = new L.LayerGroup()
			@layersControls.addOverlay(@treesOverlay, Translation.get("trees"))

			@greenSpacesOverlay = new L.LayerGroup()
			@layersControls.addOverlay(@greenSpacesOverlay, Translation.get("greenSpaces"))

			@fountainsOverlay = new L.LayerGroup()
			@layersControls.addOverlay(@fountainsOverlay, Translation.get("fountains"))

			@gardensOverlay = new L.LayerGroup()
			@layersControls.addOverlay(@gardensOverlay, Translation.get("gardens"))

			@publicPlacesOverlay = new L.LayerGroup()
			@layersControls.addOverlay(@publicPlacesOverlay, Translation.get("publicPlaces"))

			$.get(element.data('ws-monuments'), (data) =>
				@monumentsOverlay.addLayer(new L.GeoJSON(data, {
					pointToLayer: (feature, position) ->
						return new L.Marker(position, {
							icon: Icons.redIcon
						})
					
					onEachFeature: (feature, layer) ->
						popupContent = '<h5>' + feature.properties['rdfs:label'] + '</h5>'
						popupContent += '<p>' + feature.properties['rdfs:comment'] + '</p>'
						popupContent += '<p><a target="_blank" href="' + feature.properties['foaf:isPrimaryTopicOf'] + '">' + Translation.get("wikiLink") + '</a></p>';

						layer.bindPopup(popupContent)

						if feature.properties and feature.properties.style and layer.setStyle
							layer.setStyle(feature.properties.style)
				})))

			$.get(element.data('ws-trees'), (data) =>
				@treesOverlay.addLayer(new L.GeoJSON(data, {
					pointToLayer: (feature, position) ->
						return new L.Marker(position, {
							icon: Icons.treeIcon
						})
					
					onEachFeature: (feature, layer) ->
						popupContent = '<h5>' + feature.properties['arbre:nomUsuel'] + '</h5>'
						popupContent += '<p>' + feature.properties['rdfs:comment'] + '</p>'
						if feature.properties['owl:sameAs']
							popupContent += '<p><a target="_blank" href="' + feature.properties['owl:sameAs'].replace('http://fr.dbpedia.org/resource/', 'http://fr.wikipedia.org/wiki/') + '">' + Translation.get("wikiLink") + '</a></p>';

						layer.bindPopup(popupContent)

						if feature.properties and feature.properties.style and layer.setStyle
							layer.setStyle(feature.properties.style)
				})))

			$.get(element.data('ws-green-spaces'), (data) =>
				@greenSpacesOverlay.addLayer(new L.GeoJSON(data, {
					pointToLayer: (feature, position) ->
						return new L.Marker(position, {
							icon: Icons.greenIcon
						})
					
					onEachFeature: (feature, layer) ->
						popupContent = '<h5>' + feature.properties['espaceVert:nom'] + '</h5>'
						popupContent += '<p>'
						if feature.properties['espaceVert:nombrePelouses'] > 0
							popupContent += feature.properties['espaceVert:nombrePelouses'] + Translation.get("lawns") + '<br />'
						if feature.properties['espaceVert:nombreFleurs'] > 0
							popupContent += feature.properties['espaceVert:nombreFleurs'] + Translation.get("flowers") + '<br />'
						if feature.properties['espaceVert:nombreArbustes'] > 0
							popupContent += feature.properties['espaceVert:nombreArbustes'] + Translation.get("shrubs") + '<br />'
						if feature.properties['espaceVert:nombreJeux'] > 0
							popupContent += feature.properties['espaceVert:nombreJeux'] + Translation.get("games") + '<br />'

						vegetables = feature.properties['espaceVert:vegetal']
						if vegetables
							popupContent += '<br /><strong>' + Translation.get("plants") + '</strong><br />'
							if vegetables.length
								for vegetable in vegetables
									popupContent += vegetable + '<br />'
							else
								popupContent += vegetables + '<br />'

						popupContent += '</p>'
						layer.bindPopup(popupContent)

						if feature.properties and feature.properties.style and layer.setStyle
							layer.setStyle(feature.properties.style)
				})))


			$.get(element.data('ws-fountains'), (data) =>
				@fountainsOverlay.addLayer(new L.GeoJSON(data, {
					pointToLayer: (feature, position) ->
						return new L.Marker(position, {
							icon: Icons.blueIcon
						})
					
					onEachFeature: (feature, layer) ->
						popupContent = '<h5>' + Translation.get("fountain") + feature.properties['rdfs:label'] + '</h5>'

						layer.bindPopup(popupContent)

						if feature.properties and feature.properties.style and layer.setStyle 
							layer.setStyle(feature.properties.style)
				})))


			$.get(element.data('ws-gardens'), (data) =>
				@gardensOverlay.addLayer(new L.GeoJSON(data, {
					pointToLayer: (feature, position) ->
						return new L.Marker(position, {
							icon: Icons.greenIcon
						})
					
					onEachFeature: (feature, layer) ->
						popupContent = '<h5>' + feature.properties['rdfs:label'] + '</h5>'
						popupContent += '<p>' + Translation.get("districtName") + feature.properties['jardin:nomQuartier'] + '</p>'

						layer.bindPopup(popupContent)

						if feature.properties and feature.properties.style and layer.setStyle
							layer.setStyle(feature.properties.style)
				})))

			$.get(element.data('ws-public-places'), (data) =>
				@publicPlacesOverlay.addLayer(new L.GeoJSON(data, {
					pointToLayer: (feature, position) ->
						return new L.Marker(position, {
							icon: Icons.redIcon
						})
					
					onEachFeature: (feature, layer) ->
						popupContent = '<h5>' + feature.properties['rdfs:label'] + '</h5>'
						popupContent += '<p>' + Translation.get("districtName") + feature.properties['lieuxPublics:nomQuartier'] + '</p>'

						layer.bindPopup(popupContent)

						if feature.properties and feature.properties.style and layer.setStyle
							layer.setStyle(feature.properties.style)
				})))
