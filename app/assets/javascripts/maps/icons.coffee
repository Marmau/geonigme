define ['leaflet'], ->

	icons = {}

	icons.smallBeigeIcon = L.icon({
		iconUrl: '/assets/leaflet/images/small-beige-marker.png',
		shadowUrl: null,
		iconSize: [20, 33],
		iconAnchor: [10, 33]
	})

	icons.smallBlackIcon = L.icon({
		iconUrl: '/assets/leaflet/images/small-black-marker.png',
		shadowUrl: null,
		iconSize: [20, 33],
		iconAnchor: [10, 33]
	})

	icons.blueIcon = L.icon({
		iconUrl: '/assets/leaflet/images/blue-marker.png',
		shadowUrl: null,
		iconSize: [25, 41],
		iconAnchor: [12, 41]
	})

	icons.greenIcon = L.icon({
		iconUrl: '/assets/leaflet/images/green-marker.png',
		shadowUrl: null,
		iconSize: [25, 41],
		iconAnchor: [12, 41]
	})

	icons.flagIcon = L.Icon.extend({
		# iconUrl: Globals.markers['flag-green'],
		# shadowUrl: null,
		# iconSize: new L.Point(32, 32),
		# iconAnchor: new L.Point(0, 32),
	})

	return icons