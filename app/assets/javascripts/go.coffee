require ['maps/base_map', 'maps/helpers', 'leaflet'], (BaseMap, Helpers) ->

	containerMap = $('#go-map')
	containerMap.height(containerMap.width() / 2)
	map = new BaseMap(containerMap)

	stopLayers = new L.LayerGroup()
	transportLayers = new L.LayerGroup()

	map.addLayer(stopLayers)
	map.addLayer(transportLayers)
	
	drawTransport = (start, target) ->
		ws = containerMap.data('ws')
		$.get ws, {
			'start': start.lat + ',' + start.lng,
			'target': target.lat + ',' + target.lng
		}, (data) ->
			data = data.tc
			geoJsonStops = new L.GeoJSON()
			geoJsonStops.on 'featureparse', (e) ->
				popupContent = 'Arrêt : ' + e.properties.name
				e.layer.bindPopup(popupContent)

			geoJsonTransport = new L.GeoJSON()
			geoJsonTransport.on 'featureparse', (e) ->
				if e.properties && e.layer.setStyle
					e.layer.setStyle({
						color: e.properties.color,
						weight: 6,
						opacity: 0.8
					})

			stopLayers.clearLayers().addLayer(geoJsonStops)
			console.log(data.stops)
			geoJsonStops.addData(data.stops)

			transportLayers.clearLayers().addLayer(geoJsonTransport)
			console.log(data.layer)
			geoJsonTransport.addData(data.layer)


	drawTransport(Helpers.stringPositionToLatLng('3.886247999999995,43.615221'), Helpers.stringPositionToLatLng('3.880695104598999, 43.60927264556493'));