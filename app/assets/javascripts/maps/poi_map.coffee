define ['jquery', 'leaflet'], ($) ->
	class PoiMap extends BaseMap
		constructor: (element) ->
			super element[0]
			@layersControls = new L.Control.Layers();
			@map.addControl(@layersControls);

			@groups = {
			  'Arbres': new L.LayerGroup(),
			  'Fontaines': new L.LayerGroup(),
			  'Jardins': new L.LayerGroup(),
			  'Lieux Publics': new L.LayerGroup(),
			};