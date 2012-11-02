require ['maps/base_map', 'maps/helpers', 'maps/icons', 'leaflet'], (BaseMap, Helpers, Icons) ->

	containerMap = $('#go-map')
	containerMap.height(containerMap.width() / 2)
	map = new BaseMap(containerMap)

	stopLayers = new L.LayerGroup()
	transportLayers = new L.LayerGroup()

	map.addLayer(stopLayers)
	map.addLayer(transportLayers)

	writeTransport = (routes) ->
		container = $('#follow')
		containerTemplate = container.find('.template')
		containerSteps = container.find('.itinerary')
		cost = 0

		i = 0
		steps = []
		while i < routes.length
			cost += routes[i].cost

			if routes[i].type == 'tostop'
				steps.push(containerTemplate.find('.pedestrian').html()
					.replace('__stop__', routes[i].ref)
					.replace('__line__', routes[i + 2].ref)
					.replace('__type__', routes[i + 2].type))
			else if routes[i].type == 'stop'
				steps.push(containerTemplate.find('.exit').html()
					.replace('__stop__', routes[i].ref))
			else if routes[i].type == 'bus'
				steps.push(containerTemplate.find('.bus').html()
					.replace('__line__', routes[i].ref)
					.replace('__target__', routes[i].type))
			else if routes[i].type == 'tram'
				steps.push(containerTemplate.find('.tram').html()
					.replace('__line__', routes[i].ref)
					.replace('__target__', routes[i].target))

			i++

		steps.push(containerTemplate.find('.target').html())

		containerSteps.empty()
		for step in steps
			containerSteps.append($('<li></li>').html(step))

	
	drawTransport = (start, target) ->
		ws = containerMap.data('ws')
		$.get ws, {
			'start': start.lng + ',' + start.lat,
			'target': target.lng + ',' + target.lat
		}, (data) ->
			data = data.tc
			geoJsonStops = new L.GeoJSON(data.stops, {
				onEachFeature: (feature, layer) ->
					popupContent = 'Arrêt : ' + feature.properties.name
					layer.bindPopup(popupContent)
			})

			geoJsonTransport = new L.GeoJSON(data.layer, {
				style: (feature) ->
					return {
						color: feature.properties.color,
						weight: 6,
						opacity: 0.8
					}
			})

			stopLayers.clearLayers().addLayer(geoJsonStops)
			transportLayers.clearLayers().addLayer(geoJsonTransport)

			map.fitBounds(new L.LatLngBounds(start, target));
			writeTransport(data.routes)


	Helpers.getCurrentPosition (startPosition) ->
		if not startPosition
			return

		targetPosition = Helpers.stringPositionToLatLng(containerMap.data('target'))
		targetAccuracy = containerMap.data('target-accuracy')

		startMarker = new L.Marker(startPosition, {
			icon: Icons.greenFlagIcon
		}).addTo(map)
		startMarker.bindPopup('Départ')

		accuracyCircle = new L.Circle(targetPosition, targetAccuracy, {
			color: '#B94A48',
			weight: 2,
			opacity: 1,
			fill: true,
			fillColor: '#B94A48',
			fillOpacity: 0.2
		}).addTo(map)

		targetMarker = new L.Marker(targetPosition, {
			icon: Icons.redFlagIcon
		}).addTo(map)
		targetMarker.bindPopup('Arrivée')

		drawTransport(startPosition, targetPosition)

