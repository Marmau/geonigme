define ['js_messages_en', 'js_messages_fr'], (LangEN, LangFR)->	
	Translation = {}	
	
	Translation.get = (message) ->
		if navigator.language >= "en"
			return LangEN[message]
		else
			return LangFR[message]
		
	return Translation
			