define ['maps/icons', 'leaflet'], (Icons) ->
	
	Helpers = {}
	
	Helpers.stringPositionToLatLng = (stringArea) ->
		coords = stringArea.split(',')
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

		rectangleArea.bindPopup('Zone de la chasse')

		map.addLayer(rectangleArea)
		map.fitBounds(bounds)

	Helpers.drawStartStep = (map, start, accuracy) ->
		circle = new L.Circle(start, accuracy, {
			color: '#41c453',
			weight: 2,
			opacity: 1
			fill: true,
			fillColor: '#41c453',
			fillOpacity: 0.2
		})

		marker = new L.Circle(start, 4, {
			color: '#41c453',
			weight: '2',
			opacity: 1,
			fill: true,
			fillColor: '#41c453',
			fillOpacity: 0.9
		})

		marker.bindPopup('Départ de l\'étape')
		circle.bindPopup('Départ de l\'étape')

		map.addLayer(circle)
		map.addLayer(marker)

	return Helpers