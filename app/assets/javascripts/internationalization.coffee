define ['js_messages_en', 'js_messages_fr'], (LangEN, LangFR)->	
	Translation = {}	
	
	Translation.get = (message) ->
		if navigator.language.indexOf("en") != -1 
			console.log("EN")
			return LangEN[message]
		else
			console.log("FR")
			return LangFR[message]
		
	return Translation
			