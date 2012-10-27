require ['masonry'], ->

	$('#container-hunt-box').masonry({
		itemSelector: '.hunt-box',
		isAnimated: true,

		columnWidth: (containerWidth) ->
			if containerWidth > 1300
				$('#container-hunt-box .hunt-box').css('width', 250)
				return containerWidth / 5
			else if containerWidth > 1000
				$('#container-hunt-box .hunt-box').css('width', 240)
				return containerWidth / 4
			else if containerWidth > 600
				$('#container-hunt-box .hunt-box').css('width', '45%')
				return containerWidth / 2
			else 
				$('#container-hunt-box .hunt-box').css('width', '100%')
				return containerWidth
	})

	$('#container-hunt-box .hunt-box').each (i) ->
		setTimeout =>
			$(this).animate({
				opacity: 1	
			}, 500)
		, Math.random() * i * 150