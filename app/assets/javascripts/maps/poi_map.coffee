define ['maps/base_map', 'maps/icons', 'leaflet'], (BaseMap, Icons) ->
	class PoiMap extends BaseMap
		constructor: (element) ->
			super element

			@layersControls = new L.Control.Layers()
			@addControl(@layersControls)

			@monumentsOverlay = new L.LayerGroup()
			@layersControls.addOverlay(@monumentsOverlay, 'Monuments')

			@treesOverlay = new L.LayerGroup()
			@layersControls.addOverlay(@treesOverlay, 'Arbres')

			@fountainsOverlay = new L.LayerGroup()
			@layersControls.addOverlay(@fountainsOverlay, 'Fontaines')

			@gardensOverlay = new L.LayerGroup()
			@layersControls.addOverlay(@gardensOverlay, 'Jardins')

			@publicPlacesOverlay = new L.LayerGroup()
			@layersControls.addOverlay(@publicPlacesOverlay, 'Lieux publics')

			$.get(element.data('ws-monuments'), (data) =>
				@monumentsOverlay.addLayer(new L.GeoJSON(data, {
					pointToLayer: (feature, position) ->
						return new L.Marker(position, {
							icon: Icons.redIcon
						})
					
					onEachFeature: (feature, layer) ->
						popupContent = '<h5>' + feature.properties['rdfs:label'] + '</h5>'
						popupContent += '<p>' + feature.properties['rdfs:comment'] + '</p>'
						popupContent += '<p><a target="_blank" href="' + feature.properties['foaf:isPrimaryTopicOf'] + '">Lien vers la page Wikipédia</a></p>';

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
							popupContent += '<p><a target="_blank" href="' + feature.properties['owl:sameAs'].replace('http://fr.dbpedia.org/resource/', 'http://fr.wikipedia.org/wiki/') + '">Lien vers la page Wikipédia</a></p>';

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
						popupContent = '<h5>Fontaine ' + feature.properties['rdfs:label'] + '</h5>'

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
						popupContent += '<p>Nom du quartier : ' + feature.properties['jardin:nomQuartier'] + '</p>'

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
						popupContent += '<p>Nom du quartier : ' + feature.properties['lieuxPublics:nomQuartier'] + '</p>'

						layer.bindPopup(popupContent)

						if feature.properties and feature.properties.style and layer.setStyle
							layer.setStyle(feature.properties.style)
				})))
