$ ->
	# Initialisation du slideshow de l'accueil
	$('#camera-wrap').camera(
		height: '350px',
		fx: 'scrollLeft',
		loaderOpacity: 1
	) if $('#camera-wrap').length > 0

	$('#camera-wrap').cameraPause()

	# Highlight le label du input auquel il est lié lorsque ce dernier est focus
	$('form input')
		.focus ->
			$('label[for="' + @id + '"]').addClass 'focus'
		.blur ->
			$('label').removeClass 'focus'