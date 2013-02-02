require ['helpers'], (Helpers) ->

	checkAnswer = (answer) ->
		ws = $('#form-check-answer').data('check-action')

		$.post ws, {
			'answer': answer
		}, (data) ->
			if data
				$('#check-answer').hide()
				alert('Vous venez de trouvez la bonne réponse, vous pouvez continuer !')
				document.location.reload(true)
			else 
				$('#check-answer').removeClass('disabled')
				alert('Ce n\'est pas la bonne réponse !')
		
	$('#check-answer').click ->
		$(this).addClass('disabled')
		if $(this).data('type-answer') == 'text'
			answer = $('#answer-text').val()
			checkAnswer(answer)
		else if $(this).data('type-answer') == 'geolocated'
			Helpers.getCurrentPosition (position) ->
				if not position
					return

				answer = position.coords.latitude + ',' + position.coords.longitude
				checkAnswer(answer)

	$('#next-clue').click ->	
		$(this).addClass('disabled')	
		ws = $(this).data('next-clue')

		$.post ws, (data) =>
			if not data
				return

			$('#no-clues').hide()
			if data.file
				li = $('#template-clue').find('.clue').clone()
				li.html(li.html().replace('__description__', data.description))
				$('#clues-list').append(li)
			else
				li = $('#template-clue').find('.clue').clone()
				li.html(li.html().replace('__description__', data.description))
				$('#clues-list').append(li)

			if $('#clues-list > li').size() == $('#clues-list').data('number')
				$(this).hide()

			$(this).removeClass('disabled')

	$('#skip-enigma').click ->
		if confirm('Voulez-vous vraiment passer cette énigme ?')
			document.location = $(this).data('url')