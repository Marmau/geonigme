define ['js_messages_en', 'js_messages_fr'], (LangEN, LangFR)->	
	Translation = {}	
	
	Translation.get = (message) ->
		if navigator.language.indexOf("en") != -1 
			return LangEN[message]
		else
			return LangFR[message]
		
	return Translation
			