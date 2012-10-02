# Affiche la map de façon optimale
require ['jquery'], ($) ->

	marginTop = 20

	footerHeight = $('footer').outerHeight(true)
	mapElement = $('.fixed-map')
	mapParentElement = mapElement.parent()

	mapElement.css({
		position: 'absolute'
	})

	mapParentElement.css({
		position: 'relative'
	})
	
	
	displayMap = ->
		windowScroll = $(window).scrollTop()
		windowHeight = $(window).height()
		mapWrapperPositionTop = mapParentElement.position().top;
		
		if (mapWrapperPositionTop - windowScroll - marginTop > 0)
			mapHeight = windowHeight - mapWrapperPositionTop + windowScroll - footerHeight
			mapElement.height(mapHeight)
			mapParentElement.height(mapHeight)
			mapElement.css({
				top: 0
			})
		else
			mapHeight = windowHeight - marginTop - footerHeight
			mapElement.height(mapHeight)
			mapParentElement.height(mapHeight)
			positionTop = windowScroll - mapWrapperPositionTop + marginTop;
			mapElement.css({
				top: positionTop
			})
		

		mapElement.trigger('mapSizeChanged');

	displayMap()
	$(window).on 'resize scroll', ->
		displayMap()
