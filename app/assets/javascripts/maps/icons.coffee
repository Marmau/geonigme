define ['leaflet'], ->

	Icons = {}

	Icons.smallBeigeIcon = L.icon({
		iconUrl: '/assets/leaflet/images/small-beige-marker.png',
		shadowUrl: null,
		iconSize: [20, 33],
		iconAnchor: [10, 33]
	})

	Icons.smallBlackIcon = L.icon({
		iconUrl: '/assets/leaflet/images/small-black-marker.png',
		shadowUrl: null,
		iconSize: [20, 33],
		iconAnchor: [10, 33]
	})

	Icons.blueIcon = L.icon({
		iconUrl: '/assets/leaflet/images/blue-marker.png',
		shadowUrl: null,
		iconSize: [25, 41],
		iconAnchor: [12, 41]
	})

	Icons.greenIcon = L.icon({
		iconUrl: '/assets/leaflet/images/green-marker.png',
		shadowUrl: null,
		iconSize: [25, 41],
		iconAnchor: [12, 41]
	})

	Icons.greenFlagIcon = L.icon({
		iconUrl: '/assets/leaflet/images/green-flag.png',
		shadowUrl: null,
		iconSize: [32, 32],
		iconAnchor: [4, 32]
	})

	Icons.redFlagIcon = L.icon({
		iconUrl: '/assets/leaflet/images/red-flag.png',
		shadowUrl: null,
		iconSize: [32, 32],
		iconAnchor: [4, 32]
	})

	return Icons