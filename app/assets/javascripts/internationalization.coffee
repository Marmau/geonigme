<<<<<<< HEAD
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
=======
define ['js_messages_en', 'js_messages_fr'], (LangEN, LangFR)->	
	Translation = {}	
	
	Translation.get = (message) ->
		if navigator.language.indexOf("en") != -1 
			return LangEN[message]
		else
			return LangFR[message]
		
	return Translation
>>>>>>> 4dd15f2537432ee38fce2b50167d09765565aa0e
			