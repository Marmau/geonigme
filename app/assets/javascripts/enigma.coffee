require ['maps/base_map', 'fixed_map'], (BaseMap) ->

	# Initialisation de la map
	map = new BaseMap $('#enigma-map')

	### Evenements du formulaire ###

	# Toggle modal ajout indice
	$('#clue-type-btn-group > div').click ->
		$('.clue-controls').addClass 'hide'
		$($(this).data('selector')).removeClass 'hide'

	# Toggle type de réponse
	$('#answer-type-btn-group > div').click ->
		$('.answer-controls').addClass 'hide'
		$($(this).data('selector')).removeClass 'hide'

	# Validation de la modale
	$('#add-clue-btn').click ->
		type = $('#clue-type-btn-group > div').index('.active')