define ['leaflet'], ->

	Icons = {}

	Icons.treeIcon = L.icon({
		iconUrl: '/assets/leaflet/images/tree.png',
		shadowUrl: null,
		iconSize: [20, 30],
		iconAnchor: [11, 29]
	})


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

	Icons.whitePoint = L.icon({
		iconUrl: '/assets/leaflet/images/whitePoint.png',
		shadowUrl: null,
		iconSize: [40, 26],
		iconAnchor: [19, 14]
	})

	Icons.greenPoint = L.icon({
		iconUrl: '/assets/leaflet/images/greenPoint.png',
		shadowUrl: null,
		iconSize: [40, 26],
		iconAnchor: [19, 14]
	})

	Icons.blueIcon = L.icon({
		iconUrl: '/assets/leaflet/images/blueMarker.png',
		shadowUrl: null,
		iconSize: [41, 50],
		iconAnchor: [20, 45]
	})

	Icons.greenIcon = L.icon({
		iconUrl: '/assets/leaflet/images/greenMarker.png',
		shadowUrl: null,
		iconSize: [41, 50],
		iconAnchor: [20, 45]
	})

	Icons.redIcon = L.icon({
		iconUrl: '/assets/leaflet/images/redMarker.png',
		shadowUrl: null,
		iconSize: [41, 50],
		iconAnchor: [20, 45]
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