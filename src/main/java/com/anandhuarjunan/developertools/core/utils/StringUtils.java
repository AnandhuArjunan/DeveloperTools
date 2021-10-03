
package com.anandhuarjunan.developertools.core.utils;

import com.anzoft.commonlibs.constants.Constants;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.anandhuarjunan.developertools.core.constants.Labels;
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
	
	
	public static String appendWords(String delimiter,String...strings ) {
		
        Stream<String> words = Arrays.asList(strings).stream();

		return words.collect(Collectors.joining(delimiter));
	}
}
