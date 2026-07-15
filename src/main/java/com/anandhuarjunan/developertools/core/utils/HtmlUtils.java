
package com.anandhuarjunan.developertools.core.utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.Collections;

public class HtmlUtils {

    private HtmlUtils() {}

	
	
	public static List<Document> loadHtmlDocs(File directory) throws IOException {
		
		
		if (null != directory && directory.isDirectory()) {
			List<Document> documents = new ArrayList<>();
			File[] files = directory.listFiles();
			if (files != null) {
				for (File f : files) {
					documents.add(Jsoup.parse(f, StandardCharsets.UTF_8.toString()));
				}
			}
			return documents;
		}
		
		return Collections.emptyList();
	}
	
	
	public static Elements getElementsByTag(String tagName,Document document) {
		return document.getElementsByTag(tagName);
	}
	
	
	
	
	
}
