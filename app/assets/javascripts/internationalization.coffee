define ['JSMessagesEN', 'JSMessagesFR'], (lang_en, lang_fr)->	
	Translation = {}	
	
	Translation.get = (message) ->
		if navigator.language >= "en"
			return lang_en[message]
		else
			return lang_fr[message]
		
	return Translation
			