require ['maps/base_map', 'maps/position_accuracy', 'maps/icons', 'helpers', 'fixed_map'], (BaseMap, PositionAccuracy, Icon, helpers) ->

	container = $('#create-step')
	formPosition = container.find('#position-step')
	formPositionDisplay = container.find('#position-step-display')
	formAccuracy = container.find('#accuracy-step')

	# Initialisation de la map
	map = new BaseMap $('#step-map')
	pa = new PositionAccuracy map, formPosition, formAccuracy
	rectangleArea = null

	formPosition.change ->
		coords = helpers.stringPositionToLatLng($(this).val())
		formPositionDisplay.val(
			Math.roundFloat(coords.lat, 4) + ', ' + 
			Math.roundFloat(coords.lng, 4)
		)

	drawZoneHunt = ->
		bounds = helpers.stringAreaToLatLngBounds(container.data('area'))

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

		if (formPosition.val() != '' and formAccuracy.val() != '') 	
			pa.drawWithValueForms()
		else
			pa.drawCenterBounds(bounds)
		
	drawZoneHunt()
	pa.displayMarkerCenter()


