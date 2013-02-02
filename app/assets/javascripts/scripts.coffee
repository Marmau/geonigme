Math.roundFloat = (number, digits) ->
  	return Math.round(number * Math.pow(10, digits)) / Math.pow(10, digits)


require ['jquery', 'autosize'], ($) ->

	# Hauteur automatique pour les textarea
	$('textarea').autosize()

	$(document).on 'focus', 'input, textarea', ->
		$('label[for="' + @id + '"]').addClass('focus')
		$(this).siblings().addClass('focus')
	

	$(document).on 'blur', 'input, textarea', ->
		$('label').removeClass('focus')
		$(this).siblings().removeClass('focus')
	