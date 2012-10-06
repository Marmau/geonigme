define ['leaflet'], ->
	
	helpers = {}
	
	helpers.stringPositionToLatLng = (stringArea) ->
		coords = stringArea.split(',')
		return new L.LatLng(coords[0], coords[1])


	helpers.stringAreaToLatLngBounds = (stringArea) ->
		coords = (couple.split(',') for couple in stringArea.split('|'))
		return new L.LatLngBounds(new L.LatLng(coords[0][0], coords[0][1]), new L.LatLng(coords[1][0], coords[1][1]))

	return helpers