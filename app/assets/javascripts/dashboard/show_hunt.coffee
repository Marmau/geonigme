require ['maps/poi_map', 'maps/icons', 'helpers', 'internationalization', 'fixed_map'], (PoiMap, Icons, Helpers, Translation) ->

	map = new PoiMap $('#show-hunt-map')
	
	stepLayers = null
	geolocatedAnswerLayers = null

	displayStep = (position, accuracy) ->
		if stepLayers
			map.removeLayer(stepLayers.circle)
			map.removeLayer(stepLayers.marker)
		
		if position and accuracy
			stepLayers = Helpers.drawStartStep(map, Helpers.stringPositionToLatLng(position), accuracy)

	displayGeolocatedAnswer = (position, accuracy) ->
		if geolocatedAnswerLayers
			map.removeLayer(geolocatedAnswerLayers.circle)
			map.removeLayer(geolocatedAnswerLayers.marker)
		
		if position and accuracy
			geolocatedAnswerLayers = Helpers.drawGeolocatedAnswer(map, Helpers.stringPositionToLatLng(position), accuracy)


	container = $('#show-hunt')

	bounds = Helpers.stringAreaToLatLngBounds(container.data('area-hunt'))
	areaHunt = new L.Rectangle(bounds, {
		clickable: true,
		color: '#555555',
		fill: true,
		fillColor: '#555555',
		fillOpacity: 0.05
	}).addTo(map)
	areaHunt.bindPopup(Translation.get("huntArea"))
	map.fitBounds(bounds)

	accordionEnigmas = container.find('.accordion-enigmas')

	accordionEnigmas.find('.accordion-body').on 'show', ->
		displayGeolocatedAnswer($(this).data('answer-position'), $(this).data('answer-accuracy'))

	accordionEnigmas.find('.accordion-body').on 'hide', ->
		displayGeolocatedAnswer(null, null)

	container.find('#select-step select').change ->
		accordionEnigmas.find('.accordion-body.in').collapse('hide')
		container.find('.steps .step').hide();

		stepElement = container.find('.steps #step' + $(this).val())
		stepElement.fadeIn('fast') 

		displayGeolocatedAnswer(null, null)
		displayStep(stepElement.data('start-position'), stepElement.data('start-accuracy'))

	$('#select-step select').trigger 'change'