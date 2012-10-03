require ['jquery'], ($) ->
	$.fn.renumber = (selector) -> 
		$(this).each (i) ->
			$(this).find(selector).each (j) ->
				index = i + j
				$(this).attr('name', $(this).attr('name').replace(new RegExp('\\[(.+)?\\]', 'g'), '[' + index + ']'))