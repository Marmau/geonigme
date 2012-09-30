require ['jquery'], ($) ->

	# Highlight le label du input auquel il est lié lorsque ce dernier est focus
	$('form input, form textarea')
		.focus ->
			$('label[for="' + @id + '"]').addClass('focus')
			$(this).siblings().addClass('focus')
		.blur ->
			$('label').removeClass('focus')
			$(this).siblings().removeClass('focus')
