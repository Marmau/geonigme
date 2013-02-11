define ['maps/icons', 'internationalization','leaflet'], (Icons, Translation) ->
	
	Helpers = {}
	
	Helpers.stringPositionToLatLng = (stringPosition) ->
		coords = stringPosition.split(',')
		return new L.LatLng(coords[0], coords[1])


	Helpers.stringAreaToLatLngBounds = (stringArea) ->
		coords = (couple.split(',') for couple in stringArea.split('|'))
		return new L.LatLngBounds(new L.LatLng(coords[0][0], coords[0][1]), new L.LatLng(coords[1][0], coords[1][1]))

	Helpers.drawZoneHunt = (map, bounds) ->
		rectangleArea = new L.Rectangle(bounds, {
			clickable: true,
			color: '#555555',
			fill: true,
			fillColor: '#555555',
			fillOpacity: '0.05'
		})

		rectangleArea.bindPopup(Translation.get("huntArea"))

		map.addLayer(rectangleArea)
		map.fitBounds(bounds)

	Helpers.drawStartStep = (map, start, accuracy) ->
		circle = new L.Circle(start, accuracy, {
			color: '#41c453',
			weight: 2,
			opacity: 1,
			fill: true,
			fillColor: '#41c453',
			fillOpacity: 0.4
		})

		marker = new L.Marker(start, {
			icon: Icons.greenFlagIcon,
		})

		marker.bindPopup(Translation.get("stepStart"))
		circle.bindPopup(Translation.get("stepStart"))

		map.addLayer(circle)
		map.addLayer(marker)

		return {
			'circle': circle,
			'marker': marker 
		}

	Helpers.drawGeolocatedAnswer = (map, start, accuracy) ->
		circle = new L.Circle(start, accuracy, {
			color: '#B94A48',
			weight: 2,
			opacity: 1,
			fill: true,
			fillColor: '#B94A48',
			fillOpacity: 0.2
		})

		marker = new L.Marker(start, {
			icon: Icons.redFlagIcon,
		})

		marker.bindPopup(Translation.get("end"))
		circle.bindPopup(Translation.get("end"))

		map.addLayer(circle)
		map.addLayer(marker)

		return {
			'circle': circle,
			'marker': marker 
		}	

	Helpers.getCurrentPosition = (callback) ->
		if (navigator.geolocation)
			navigator.geolocation.getCurrentPosition((position)-> 
				callback(position)
			, (error) ->
				callback(null)
			, {
				enableHighAccuracy: true,
				timeout: 15000,
				maximumAge: 0
			})

	Helpers.watchPosition = (callback) ->
		if (navigator.geolocation)
			return navigator.geolocation.watchPosition((position)-> 
				callback(position)
			, (error) ->
				callback(null)
			, {
				enableHighAccuracy: true,
				timeout: 10000,
				maximumAge: 0
			})

		return null

	Helpers.clearWatchPosition = (id) ->
		if (navigator.geolocation)
			navigator.geolocation.clearWatch(id)

	return Helpers