requirejs.config({
	baseUrl: '/assets/javascripts',

	paths: {
		jquery: 'jquery-1.8.2.min',
		jquery_easing: 'jquery.easing-1.3',
		bootstrap: 'bootstrap.min',
		camera: 'camera.min',
		autosize: 'jquery.autosize-min',
		leaflet: '../leaflet/leaflet',
	}
})

require ['jquery','bootstrap.min'], ($) ->
	$ ->
		require ['scripts']
		require ($.trim $('script').data('start') + ',').split(',')