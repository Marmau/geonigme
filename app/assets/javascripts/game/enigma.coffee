require ['helpers', 'spinner', 'internationalization'], (Helpers, Spinner, Translation) ->

	checkAnswer = (answer, type) ->
		ws = $('#form-check-answer').data('check-action')

		$.post ws, {
			'answer': answer
		}, (data) ->
			Spinner.stop()
			if data
				if type == 'text'
					alert(Translation.get("goodAnswer"))
				else if type == 'geolocated'
					alert(Translation.get("goodPlace"))
				document.location.reload(true)
			else 
				$('#check-answer').removeClass('disabled')
				if type == 'text'
					alert(Translation.get("badAnswer"))
				else if type == 'geolocated'
					alert(Translation.get("badPlace"))
		
	$('#check-answer').click ->
		$(this).addClass('disabled')
		Spinner.start()
		type = $(this).data('type-answer')
		if type == 'text'
			answer = $('#answer-text').val()
			checkAnswer(answer, type)
		else if type == 'geolocated'
			Helpers.getCurrentPosition (position) ->
				if not position
					return

				answer = position.coords.latitude + ',' + position.coords.longitude
				checkAnswer(answer, type)

	$(document).on 'click', '.display-picture', ->
		if $(this).siblings('.display-hide-picture').hasClass('hide')
			$(this).siblings('.display-hide-picture').removeClass('hide')
			$(this).html($(this).data('hide'))
		else
			$(this).siblings('.display-hide-picture').addClass('hide')
			$(this).html($(this).data('show'))

	$(document).on 'click', '.display-sound', ->
		$('.playAudio').get(0).play()

	$('#next-clue').submit ->	
		Spinner.start()
		$(this).addClass('disabled')	
		ws = $(this).attr('action')

		$.post ws, $(this).serializeArray(), (data) =>
			if not data
				return

			$('#no-clues').hide()
			if data.file
				if data.type == 'picture'
					li = $('#template-picture-clue').find('.clue').clone()
					li.find('img').attr('alt', data.description)
								.attr('src', data.file)
					li.html(li.html().replace(/__description__/, data.description))
					$('#clues-list').append(li)
				else if data.type == 'sound'
					li = $('#template-sound-clue').find('.clue').clone()
					li.find('audio source').attr('src', data.file)
					li.html(li.html().replace(/__description__/, data.description))
					$('#clues-list').append(li)
			else
				li = $('#template-clue').find('.clue').clone()
				li.html(li.html().replace(/__description__/, data.description))
				$('#clues-list').append(li)

			if $('#clues-list > li').size() == $('#clues-list').data('number')
				$('#skip').show()
				$(this).hide()

			$(this).removeClass('disabled')
			Spinner.stop()

		return false

	$('#skip-enigma').submit ->
		return confirm(Translation.get("passEnigma"))
