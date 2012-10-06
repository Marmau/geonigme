define ['jquery', 'helpers', 'leaflet'], ($, helpers) ->
	class PositionAccuracy
		constructor: (@map, @formPosition, @formAccuracy) ->
			@markerCenter = new L.Marker(new L.LatLng(43.64, 3.93), {
				draggable: true
			})

			@markerRadius = new L.Marker(new L.LatLng(43.635, 3.928), {
				draggable: true
			})

			@circleArea = new L.Circle(@markerCenter.getLatLng(), @markerCenter.getLatLng().distanceTo(@markerRadius.getLatLng()))

			@markerCenter.on 'drag', =>
				@moveCircleArea()
				@write()

			@markerRadius.on 'drag', =>
				@drawCircleArea()
				@write()

			@oldPosition = @markerCenter.getLatLng()

		drawCenterBounds: (bounds) ->
			latLngMarker = bounds.getCenter()
			@markerCenter.setLatLng(latLngMarker)
			@markerRadius.setLatLng(new L.LatLng(latLngMarker.lat - 0.0005, latLngMarker.lng - 0.0005))
			@oldPosition = @markerCenter.getLatLng()
			@drawCircleArea()
			@write()

		drawWithValueForms: ->
			if @formPosition.val() == '' or @formAccuracy.val() == '' 
				return

			latLngCenter = helpers.stringPositionToLatLng(@formPosition.val())

			latStart = latLngCenter.lat * Math.PI / 180
			lngStart = latLngCenter.lng * Math.PI / 180
			R = 6378137
			dist = accuracy / R
			brng = 2.35; # radians = 135 degres
			lat = Math.asin(Math.sin(latStart) * Math.cos(dist)
					  + Math.cos(latStart) * Math.sin(dist) * Math.cos(brng))
			lng = lngStart + Math.atan2(
					  Math.sin(brng) * Math.sin(dist) * Math.cos(latStart),
					  Math.cos(dist) - Math.sin(latStart) * Math.sin(lat)
					)

			latEnd = lat * 180 / Math.PI
			lngEnd = lng * 180 / Math.PI
			latLngRadius = new L.LatLng(latEnd, lngEnd)

			@markerCenter.setLatLng(latLngCenter)
			@markerRadius.setLatLng(latLngRadius)

			@oldPosition = @markerCenter.getLatLng()
			@drawCircleArea()
			@write()
			
		moveCircleArea: ->
			dlat = @markerCenter.getLatLng().lat - @oldPosition.lat
			dlng = @markerCenter.getLatLng().lng - @oldPosition.lng

			@markerRadius.setLatLng(new L.LatLng(@markerRadius.getLatLng().lat + dlat, @markerRadius.getLatLng().lng + dlng))

			@oldPosition = @markerCenter.getLatLng()

			@drawCircleArea()

		drawCircleArea: ->
			@circleArea.setLatLng(@markerCenter.getLatLng())
			@circleArea.setRadius(@markerCenter.getLatLng().distanceTo(@markerRadius.getLatLng()))

		write: ->
			@formPosition.val(@markerCenter.getLatLng().lat + ',' + @markerCenter.getLatLng().lng)
			@formPosition.trigger('change')

			@formAccuracy.val(Math.round(@markerRadius.getLatLng().distanceTo(@markerCenter.getLatLng())))

		displayMarkerCenter: ->
			@map.addLayer(@markerCenter);
			@map.addLayer(@markerRadius);
			@map.addLayer(@circleArea);
  
		hideMarkerCenter: ->
			@map.removeLayer(@markerCenter);
			@map.removeLayer(@markerRadius);
			@map.removeLayer(@circleArea);
