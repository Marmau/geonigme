requirejs.config({
	baseUrl: '/assets/javascripts',

	paths: {
		jquery: 'jquery-1.9.0.min',
		jquery_mobile: 'jquery.mobile-1.2.0.min',
		jquery_easing: 'jquery.easing-1.3',
		bootstrap: 'bootstrap',
		camera: 'camera.min',
		masonry: 'jquery.masonry.min',
		autosize: 'jquery.autosize-min',
		leaflet: '../leaflet/leaflet-src',
	}
})

require ['jquery'], ->
	require ['bootstrap'], ->
		$ ->
			require ['scripts']
			require ($.trim $('script[data-start]').data('start') + ',').split(',')