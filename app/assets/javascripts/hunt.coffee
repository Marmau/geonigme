require ['maps/base_map', 'fixed_map'], (BaseMap) ->

	stringAreaToLatLngBounds = (stringArea) ->
		coords = (couple.split(',') for couple in stringArea.split('|'))
		return new L.LatLngBounds(new L.LatLng(coords[0][0], coords[0][1]), new L.LatLng(coords[1][0], coords[1][1]))

	# Choix du niveau
	if '' == level = $('#level-hunt').val()
		level = 0
		$('#level-hunt').val(level)

	$('#level-hunt-group > div').removeClass('active')
	$('#level-hunt-group > div').eq(level).addClass('active')

	$('#level-hunt-group > div').click ->
		$('#level-hunt').val($(this).index())


	container = $('#create-hunt');
	formArea = container.find('#area-hunt')
	formAreaDisplay = container.find('#area-hunt-display')
	
	# Initialisation de la map
	map = new BaseMap $('#hunt-map')

	# Layers
	rectangleArea = null
	markerSouthWest = null
	markerNorthEast = null

	if area = formArea.val()
		bounds = stringAreaToLatLngBounds area
	else
		bounds = new L.LatLngBounds(new L.LatLng(43.6, 3.851), new L.LatLng(43.622, 3.896))

	map.fitBounds(bounds);

	optionsMarker = {
		draggable: true
	}

	drawRectangleAreaAndWriteBounds = ->
		if rectangleArea and map.hasLayer(rectangleArea)
			map.removeLayer(rectangleArea)

		bounds = new L.LatLngBounds(
			markerSouthWest.getLatLng(),
			markerNorthEast.getLatLng()
		)

		rectangleArea = new L.Rectangle(bounds)

		map.addLayer(rectangleArea)

		bounds = new L.LatLngBounds(
			markerSouthWest.getLatLng(),
			markerNorthEast.getLatLng()
		)

		formArea.val(
			bounds.getSouthWest().lat + ',' +
			bounds.getSouthWest().lng + '|' +
			bounds.getNorthEast().lat + ',' +
			bounds.getNorthEast().lng
		)

		formArea.trigger('change')	    

	onDrag = ->
		drawRectangleAreaAndWriteBounds()

	# Zone d'affichage
	formArea.change ->
		bounds = stringAreaToLatLngBounds $(this).val()
		formAreaDisplay.val(
			Math.roundFloat(bounds.getSouthWest().lat, 4) + ', ' + 
			Math.roundFloat(bounds.getSouthWest().lng, 4) + ' | ' + 
			Math.roundFloat(bounds.getNorthEast().lat, 4) + ', ' + 
			Math.roundFloat(bounds.getNorthEast().lng, 4)
		)
		
	markerSouthWest = new L.Marker(bounds.getSouthWest(), optionsMarker)
	markerSouthWest.on 'drag', onDrag

	markerNorthEast = new L.Marker(bounds.getNorthEast(), optionsMarker)
	markerNorthEast.on 'drag', onDrag

	map.addLayer(markerSouthWest)
	map.addLayer(markerNorthEast)

	drawRectangleAreaAndWriteBounds()


