/*
 * Copyright 2021 : Anandhu Arjunan
 * Made with Love In India.
 * Email : anandhuka97@gmail.com
╚🍧  🎀  𝐵𝓎  𝒜𝓃𝒶𝓃𝒹𝒽𝓊 𝒜𝓇𝒿𝓊𝓃𝒶𝓃  🎀  🍧 
 */
package com.anzoft.developertools.utils;

import com.anzoft.commonlibs.constants.Constants;
import com.anzoft.developertools.app.messages.Labels;

public class StringUtils {
	public static String convertToDelimiterType(String selectedItem) {
		if(Labels.NEW_LINE.equalsIgnoreCase(selectedItem)) {
			return Constants.CHAR_NEW_LINE;
		}
		else if(Labels.COMMA.equalsIgnoreCase(selectedItem)) {
			return Constants.COMMA;
		}	
		else if(Labels.PIPE.equalsIgnoreCase(selectedItem)) {
			return Constants.PIPE;
		}
		else if(Labels.TAB.equalsIgnoreCase(selectedItem)) {
			return Constants.TAB;
		}	
		else if(Labels.SPACE.equalsIgnoreCase(selectedItem)) {
			return Constants.SPACE;
		}
		else if(Labels.DOT.equalsIgnoreCase(selectedItem)) {
			return Constants.DOT;
		}

		else 
			return null;
		
	}
	
	public static String convertToDirectCharType(String selectedItem) {
		if(Labels.NEW_LINE.equalsIgnoreCase(selectedItem)) {
			return Constants.CHAR_NEW_LINE;
		}
		else if(Labels.COMMA.equalsIgnoreCase(selectedItem)) {
			return Constants.COMMA;
		}	
		else if(Labels.PIPE.equalsIgnoreCase(selectedItem)) {
			return Constants.CHAR_PIPE;
		}
		else if(Labels.TAB.equalsIgnoreCase(selectedItem)) {
			return Constants.CHAR_TAB;
		}	
		else if(Labels.SPACE.equalsIgnoreCase(selectedItem)) {
			return Constants.CHAR_SPACE;
		}
		else if(Labels.DOT.equalsIgnoreCase(selectedItem)) {
			return Constants.CHAR_DOT;
		}

		else 
			return null;
		
	}
}
