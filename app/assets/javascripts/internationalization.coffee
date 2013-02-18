define ['js_messages_en', 'js_messages_fr'], (LangEN, LangFR)->	
	Translation = {}	
	
	if !Array.indexOf
  		cpt = 0
  		Array.prototype.indexOf = (obj) ->
    		for val in this
        		cpt = cpt + 1
        		if val == obj 
          			return cpt;	
    		return -1;
	
	Translation.get = (message) ->
  		lg = if navigator.language then navigator.language else navigator.userLanguage
		if lg.indexOf("en") != -1 
			return LangEN[message]
		else
		    return LangFR[message]
		
	return Translation

			