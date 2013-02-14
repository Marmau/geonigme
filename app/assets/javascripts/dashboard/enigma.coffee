require ['maps/poi_map', 'maps/position_accuracy', 'maps/icons', 'helpers', 'renumber', 'fixed_map'], (PoiMap, PositionAccuracy, Icon, Helpers) ->

	container = $('#create-enigma')
	formPosition = container.find('#position-answer')
	formPositionDisplay = container.find('#position-answer-display')
	formAccuracy = container.find('#accuracy-answer')



	### Gestion de la map ###
	# Initialisation de la map
	map = new PoiMap $('#enigma-map')
	pa = new PositionAccuracy map, formPosition, formAccuracy

	formPosition.change ->
		coords = Helpers.stringPositionToLatLng($(this).val())
		formPositionDisplay.val(
			Math.roundFloat(coords.lat, 4) + ', ' + 
			Math.roundFloat(coords.lng, 4)
		)

	areaHunt = Helpers.stringAreaToLatLngBounds(container.data('area'))
	startPositionStep = Helpers.stringPositionToLatLng(container.data('start-position'))
	startAccuracyStep = container.data('start-accuracy')

	Helpers.drawZoneHunt(map, areaHunt)
	Helpers.drawStartStep(map, startPositionStep, startAccuracyStep)

	if formPosition.val() != '' and formAccuracy.val() != ''
		pa.drawWithValueForms()
	else
		pa.drawCenterBounds(areaHunt)

	### Evenements du formulaire ###
	
	# Suppression
	$('#suppression').submit ->
		return confirm(Translation.get("deleteHunt"))

	# Toggle type de réponse
	container.find('.answer-type-btn-group > div').click ->
		type = $(this).index()
		container.find('.answer-controls').addClass 'hide'
		container.find($(this).data('selector')).removeClass 'hide'
		$(this).parent().siblings('input').val(type)

		if type == 1
			pa.displayMarkerCenter()
		else 
			pa.hideMarkerCenter()

	# Toggle modal ajout indice
	$('.clue-type-btn-group > div').click ->
		$('.clue-controls').addClass 'hide'
		$($(this).data('selector')).removeClass 'hide'


	## Indices
	containerClues = $('#clues')
	prototypeClue = $(containerClues.data('prototype'))
	modalAddClue = $('#modal-add-clue')
	modalEditClue = $('#modal-edit-clue')

	renumberClues = ->
		containerClues.renumber('[data-renumber="type-clue"]')
		containerClues.renumber('[data-renumber="text-clue-description"]')
		containerClues.renumber('[data-renumber="file-clue-description"]')
		containerClues.renumber('[data-renumber="file-clue-link"]')

	# Validation de la modale des indices

	# Sauvegarde l'indice dans le container
	saveClue = (modal, container) ->
		type = modal.find('.clue-type-btn-group .active').index()
		container.find('.number').html(container.index() + 1)
		container.find('[data-bind="type-clue"]').val(type)
		modal.find('textarea, input').each ->
			container.find('[data-bind="' + $(this).attr('data-bind') + '"]').val($(this).val())
			$(this).val('')

		if (type == 0)
			container.find('.icon-type-clue').removeClass('icon-file').addClass('icon-comment') 
		else
			container.find('.icon-type-clue').removeClass('icon-comment').addClass('icon-file')

		renumberClues()


	$('#add-clue-btn').click ->
		container = prototypeClue.children().clone()
		
		containerClues.append(container)
		saveClue(modalAddClue, container)

		modalAddClue.modal('hide')

		false
		
	$('#update-clue-btn').click ->
		container = modalEditClue.data('container-clue')
		saveClue(modalEditClue, container)

		modalEditClue.modal('hide')

		false

	# Edition d'un indice
	$(document).on 'click', '.edit-clue-btn', ->
		container = $(this).closest('.clue')

		type = container.find('[data-bind="type-clue"]').val()

		modalEditClue.data('container-clue', container)
		modalEditClue.find('.clue-type-btn-group').children().removeClass('active')
		modalEditClue.find('.clue-controls').addClass('hide')

		modalEditClue.find('.clue-type-btn-group').children().eq(type).addClass('active')
		modalEditClue.find('.clue-controls').eq(type).removeClass('hide')

		container.find('input').each ->
			modalEditClue.find('[data-bind="' + $(this).attr('data-bind') + '"]').val($(this).val())

		modalEditClue.modal('show')

		false

	$(document).on 'click', '.remove-clue-btn', ->
		container = $(this).closest('.clue')
		container.remove();
		containerClues.find('.clue').each (i) ->
			$(this).find('.number').html(i + 1)

		modalEditClue.modal('hide')
		renumberClues()

		false


	# Click bouton plus/moins des réponses
	containerTextAnswers = $('#text-answers');

	disableLastButtonIfMax = ->
		inputs = containerTextAnswers.children('.text-answer')

		if (inputs.length >= containerTextAnswers.data('max')) 
			inputs.last()
				.children('.btn')
				.unbind()
				.addClass('disabled')
		else
			inputs.last()
				.children('.btn')
				.unbind()
				.bind('click', addTextAnswer)

		containerTextAnswers.renumber('[data-renumber="text-answer"]')

	removeTextAnswer = ->
		$(this).parent().remove();
		lastInput = containerTextAnswers.children('.text-answer').last();
		lastInput
			.children('.btn')
			.removeClass('disabled')
			.unbind()
			.bind('click', addTextAnswer)

		disableLastButtonIfMax()

	addTextAnswer = ->
		inputs = containerTextAnswers.children('.text-answer');
		lastInput = inputs.last();
		newLastInput = lastInput.clone();

		lastInput.after(newLastInput);
		lastInput.children('.btn')
			.unbind()
			.bind('click', removeTextAnswer)
			.html('<i class="icon-minus icon-white"></i>');

		newLastInput.children('input').val('');
		disableLastButtonIfMax()
		
	containerTextAnswers.children('.text-answer .btn').bind('click', removeTextAnswer)
	containerTextAnswers.children('.text-answer .btn').last().unbind().bind('click', addTextAnswer)

	disableLastButtonIfMax();
