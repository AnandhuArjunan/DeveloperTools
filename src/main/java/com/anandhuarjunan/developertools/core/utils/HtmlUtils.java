
package com.anandhuarjunan.developertools.core.utils;

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
