/*
 * Copyright 2021 : Anandhu Arjunan
 * Made with Love In India.
 * Email : anandhuka97@gmail.com
╚🍧  🎀  𝐵𝓎  𝒜𝓃𝒶𝓃𝒹𝒽𝓊 𝒜𝓇𝒿𝓊𝓃𝒶𝓃  🎀  🍧 
 */
package com.anzoft.developertools.views.tools.language;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.anzoft.developertools.cache.DataCache;
import com.anzoft.developertools.constants.CommonConstant;
import com.anzoft.developertools.exceptions.ToolError;
import com.anzoft.developertools.thread.AsyncTask;
import com.anzoft.developertools.ui.styles.DictionaryStyle;
import com.anzoft.developertools.utils.HtmlUtils;
import com.anzoft.developertools.views.AbstractTool;

import javafx.scene.Node;

public class EnglishDictionary extends AbstractTool{
	EnglishDictionaryUI dictionaryUI = new EnglishDictionaryUI();
	Map<String,Elements> dictionaryData = null;
	Set<String> sortedDictionaryWords = null;
	@SuppressWarnings("unchecked")
	@Override
	public void open() throws ToolError {
	    
	 
	    
	    
			dictionaryData = (Map<String, Elements>) DataCache.getInstance().fetch(CommonConstant.ENGLISH_DICTIONARY);
			if(null == dictionaryData) {
				try {
					dictionaryData = dataProvider();
				} catch (IOException e) {
					
				}
				DataCache.getInstance().insert(CommonConstant.ENGLISH_DICTIONARY, dictionaryData);
			}
			sortedDictionaryWords = new TreeSet<>(dictionaryData.keySet());
			dictionaryUI.addToListview(sortedDictionaryWords);
		
		}
		

	
	
	public void showResult(String keyword) {
		final StringBuilder data = new StringBuilder();
		boolean bool = false;
		
		for(Iterator<String> it = dictionaryData.keySet().iterator();it.hasNext();) {
			if(it.next().toLowerCase(Locale.ENGLISH).equalsIgnoreCase(keyword)) {
				Elements wordMeanings = dictionaryData.get(keyword);
				wordMeanings.forEach(el->{data.append(el.parentNode().toString());});
				bool = true;
			}
			
		}
		if(!bool) {
			data.append("<b>No Word Found</b>");
		}

		
		
		dictionaryUI.setWebView(data.toString());
	}
	
	


	
	
	private Map<String,Elements> dataProvider() throws IOException {
		
		Map<String,Elements> dictionary = new HashMap<>();
		List<Document> data1 = HtmlUtils.loadHtmlDocs(new File(getDataFolder()));
		for(Document document : data1) {
			Elements dictionaryWords = HtmlUtils.getElementsByTag("B",document);
			for(Element word : dictionaryWords) {
				if(dictionary.containsKey(word.text())) {
					dictionary.get(word.text()).add(word);
				}
				else {
					Elements wordMeanings= new Elements(word);
					dictionary.put(word.text(),wordMeanings);
				}
			}
		}
		
		return dictionary;
		
	}

	class EnglishDictionaryUI extends DictionaryStyle{

		@Override
		public void onSearch(String keyWord) {
			 if(!StringUtils.isEmpty(keyWord)) {
				  Set<String> filterdSortedSet = sortedDictionaryWords.stream().filter(predicate->predicate.toLowerCase().startsWith(keyWord.toLowerCase())).collect(Collectors.toSet());
				  addToListview(filterdSortedSet);
				  }
				  else {
					  addToListview(sortedDictionaryWords);
				  }
			
		}

		@Override
		public void onGO(String keyWord) {
			showResult(keyWord);

			
		}

		@Override
		public void onclickDictionaryItems(String text) {
			
			showResult(text);

		}
		
	}

	@Override
	public Node view() throws ToolError {
		return dictionaryUI;
	}
}
