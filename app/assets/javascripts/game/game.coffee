require ['maps/base_map', 'helpers', 'leaflet'], (BaseMap, Helpers) ->

	containerMap = $('#presentation-game-hunt')
	containerMap.height(Math.min(containerMap.width() / 2, 400))

	map = new BaseMap containerMap

	bounds = Helpers.stringAreaToLatLngBounds(containerMap.data('area-hunt'))
	areaHunt = new L.Rectangle(bounds, {
		clickable: true,
		color: '#555555',
		fill: true,
		fillColor: '#555555',
		fillOpacity: 0.05
	}).addTo(map)

	map.fitBounds(bounds)