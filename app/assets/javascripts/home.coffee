require ['jquery', 'jquery_easing', 'camera'], ($) ->
	$('#camera-wrap').camera(
		height: '350px',
		fx: 'scrollLeft',
		loaderOpacity: 1
	) 

	$('#camera-wrap').cameraPause()