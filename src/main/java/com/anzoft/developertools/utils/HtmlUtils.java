/*
 * Copyright 2021 : Anandhu Arjunan
 * Made with Love In India.
 * Email : anandhuka97@gmail.com
╚🍧  🎀  𝐵𝓎  𝒜𝓃𝒶𝓃𝒹𝒽𝓊 𝒜𝓇𝒿𝓊𝓃𝒶𝓃  🎀  🍧 
 */
package com.anzoft.developertools.utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class HtmlUtils {

	
	
	public static List<Document> loadHtmlDocs(File directory) throws IOException {
		
		
		if(null != directory && directory.isDirectory() ) {
			
			List<Document> documents = new ArrayList<>();
			for(File f : directory.listFiles()) {
				documents.add(Jsoup.parse(f, StandardCharsets.UTF_8.toString()));
			}
			
			return documents;
			
		}
		
		return new ArrayList<>();
	}
	
	
	public static Elements getElementsByTag(String tagName,Document document) {
		return document.getElementsByTag(tagName);
	}
	
	
	
	
	
}
