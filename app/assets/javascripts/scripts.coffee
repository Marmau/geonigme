require ['jquery', 'autosize'], ($) ->

	# Hauteur automatique pour les textarea
	$('textarea').autosize()

	# Highlight le label du input auquel il est lié lorsque ce dernier est focus
	$('input, textarea')
		.live 'focus', ->
			$('label[for="' + @id + '"]').addClass('focus')
			$(this).siblings().addClass('focus')
		.live 'blur', ->
			$('label').removeClass('focus')
			$(this).siblings().removeClass('focus')
