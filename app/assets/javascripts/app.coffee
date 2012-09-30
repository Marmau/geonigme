requirejs.config({
	baseUrl: '/assets/javascripts',

	paths: {
		jquery: 'jquery-1.8.2.min',
		jquery_easing: 'jquery.easing-1.3',
		bootstrap: 'bootstrap.min',
		camera: 'camera.min',
		leaflet: '../leaflet/leaflet',
	}
})

require ['jquery'], ($) ->
	$ ->
		require ['scripts', 'bootstrap.min']
		require ($.trim $('script').data('start') + ',').split(',')