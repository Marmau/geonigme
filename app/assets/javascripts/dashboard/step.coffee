require ['maps/poi_map', 'maps/position_accuracy', 'maps/icons', 'helpers', 'fixed_map'], (PoiMap, PositionAccuracy, Icon, Helpers) ->

	container = $('#create-step')
	formPosition = container.find('#position-step')
	formPositionDisplay = container.find('#position-step-display')
	formAccuracy = container.find('#accuracy-step')

	# Initialisation de la map
	map = new PoiMap $('#step-map')
	pa = new PositionAccuracy map, formPosition, formAccuracy

	formPosition.change ->
		coords = Helpers.stringPositionToLatLng($(this).val())
		formPositionDisplay.val(
			Math.roundFloat(coords.lat, 4) + ', ' + 
			Math.roundFloat(coords.lng, 4)
		)

	bounds = Helpers.stringAreaToLatLngBounds(container.data('area'))
	Helpers.drawZoneHunt(map, bounds)

	if formPosition.val() != '' and formAccuracy.val() != ''	
		pa.drawWithValueForms()
	else
		pa.drawCenterBounds(bounds)
		
	pa.displayMarkerCenter()


