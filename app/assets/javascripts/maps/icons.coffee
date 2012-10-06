define ['leaflet'], ->

	icons = {}
	icons.SmallIcon = L.Icon.extend({
		# iconUrl: '/assets/images/markers...'
		# shadowUrl: null,
		# iconSize: new L.Point(25, 33),
		# iconAnchor: new L.Point(12, 33)
	})

	icons.FlagIcon = L.Icon.extend({
		# iconUrl: Globals.markers['flag-green'],
		# shadowUrl: null,
		# iconSize: new L.Point(32, 32),
		# iconAnchor: new L.Point(0, 32),
	})

	return icons