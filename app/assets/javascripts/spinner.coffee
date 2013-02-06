define ['spin'], (Spin) ->

	Spinner = {}

	opts = {
		lines: 7, # The number of lines to draw
		length: 0, # The length of each line
		width: 12, # The line thickness
		radius: 8, # The radius of the inner circle
		corners: 0, # Corner roundness (0..1)
		rotate: 56, # The rotation offset
		color: '#002e86', # #rgb or #rrggbb
		speed: 1.1, # Rounds per second
		trail: 100, # Afterglow percentage
		shadow: true, # Whether to render a shadow
		hwaccel: true, # Whether to use hardware acceleration
		className: 'spinner', # The CSS class to assign to the spinner
		zIndex: 2e9, # The z-index (defaults to 2000000000)
		top: 'auto', # Top position relative to parent in px
		left: 'auto' # Left position relative to parent in px
	}

	spinner = new Spin(opts)

	Spinner.start = ->
		element = $('#spinner')
		element.height($(window).height())
		element.show()
		spinner.spin(document.getElementById('spinner'))

	Spinner.stop = ->
		$('#spinner').hide()
		spinner.stop()

	return Spinner