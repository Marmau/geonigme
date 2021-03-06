define ['jquery', 'leaflet'], ($) ->
	class BaseMap extends L.Map 
		constructor: (element) ->
			super element[0]

			@setMaxBounds([[43.693197, 3.703663], [43.51868, 4.033939]])
			@setView([43.613211, 3.875771], 11)
			L.tileLayer('http://{s}.tile.cloudmade.com/BC9A493B41014CAABB98F0471D759707/997/256/{z}/{x}/{y}.png', {
				attribution: '<a target="_blank" href="http://openstreetmap.org">OpenStreetMap</a> - <a target="_blank" href="http://boussole.mandarine34.fr/">Mandarine</a>',
				maxZoom: 18
			})
			.addTo(this)


			element.on 'mapSizeChanged', =>
				this.invalidateSize(true)