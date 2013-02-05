require ['helpers', 'spinner'], (Helpers, Spinner) ->

	checkAnswer = (answer, type) ->
		ws = $('#form-check-answer').data('check-action')

		$.post ws, {
			'answer': answer
		}, (data) ->
			Spinner.stop()
			if data
				if type == 'text'
					alert('Vous venez de trouvez la bonne réponse, vous pouvez continuer !')
				else if type == 'geolocated'
					alert('Vous êtes au bon endroit, vous pouvez continuer !')
				document.location.reload(true)
			else 
				$('#check-answer').removeClass('disabled')
				if type == 'text'
					alert('Ce n\'est pas la bonne réponse, mais persévérez, vous trouverez !')
				else if type == 'geolocated'
					alert('Vous n\'êtes pas au bon endroit, continuez de chercher !')
		
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

	$('#next-clue').submit ->	
		Spinner.start()
		$(this).addClass('disabled')	
		ws = $(this).attr('action')

		$.post ws, $(this).serializeArray(), (data) =>
			if not data
				return

			$('#no-clues').hide()
			if data.file
				console.log(data)
				li = $('#template-clue').find('.clue').clone()
				li.html(li.html().replace('__description__', data.description))
				$('#clues-list').append(li)
				li = $('#template-file-clue').find('.clue').clone()
				li.html(li.html().replace('__file__', data.file))
				$('#clues-list').append(li)
			else
				li = $('#template-clue').find('.clue').clone()
				li.html(li.html().replace('__description__', data.description))
				$('#clues-list').append(li)
				li = $('#template-file-clue').find('.clue').clone()
				li.html(li.html().replace('__file__', data.file))
				$('#clues-list').append(li)

			if $('#clues-list > li').size() == $('#clues-list').data('number')
				$('#skip').show()
				$(this).hide()

			$(this).removeClass('disabled')
			Spinner.stop()

		return false

	$('#skip-enigma').submit ->
		return confirm('Voulez-vous vraiment passer cette énigme ?')
