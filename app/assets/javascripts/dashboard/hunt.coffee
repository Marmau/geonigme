require ['maps/poi_map', 'helpers', 'maps/icons', 'internationalization','fixed_map'], (PoiMap, Helpers, Icons, Translation) ->

	# Choix de la langue
	if '' == language = $('#language-hunt').val()
		language = Translation.get('fr')
		$('#language-hunt').val(language)
		
	$('#language-hunt-group > div').removeClass('active')
	if language == Translation.get('fr')
		$('#language-hunt-group > div').eq(0).addClass('active')
	else
		$('#language-hunt-group > div').eq(1).addClass('active')

	$('#language-hunt-group > div').click ->
		if $(this).index() is 0
			$('#language-hunt').val(Translation.get('fr'))
		else
			$('#language-hunt').val(Translation.get('en'))
			

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
	
	# Suppression
	$('#deleteHunt').submit ->
		return confirm(Translation.get("deleteHunt"))
	
	# Initialisation de la map
	map = new PoiMap $('#hunt-map')

	# Layers
	rectangleArea = null
	markerSouthWest = null
	markerNorthEast = null

	if area = formArea.val()
		bounds = Helpers.stringAreaToLatLngBounds area
	else
		bounds = new L.LatLngBounds(new L.LatLng(43.6, 3.851), new L.LatLng(43.622, 3.896))

	map.fitBounds(bounds);

	optionsMarker = {
		draggable: true,
		icon: Icons.blueIcon
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
		bounds = Helpers.stringAreaToLatLngBounds $(this).val()
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


