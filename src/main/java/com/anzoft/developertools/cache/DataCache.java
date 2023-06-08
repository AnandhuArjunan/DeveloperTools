/*
 * Copyright 2021 : Anandhu Arjunan
 * Made with Love In India.
 * Email : anandhuka97@gmail.com
╚🍧  🎀  𝐵𝓎  𝒜𝓃𝒶𝓃𝒹𝒽𝓊 𝒜𝓇𝒿𝓊𝓃𝒶𝓃  🎀  🍧 
 */
package com.anzoft.developertools.cache;

import java.util.HashMap;
import java.util.Map;


public class DataCache {
	private static DataCache cache = null; 
	private Map<String,Object> data = null;
	private DataCache() 
	{ 
		 data = new HashMap<>();
	} 

	public static synchronized DataCache getInstance() 
	{ 
		if (cache == null) 
			cache = new DataCache(); 

		return cache; 
	}

	public void insert(String key,Object obj) {
		data.put(key, obj);
	}
	
	public Object fetch(String key) {
		if(data.containsKey(key)) {
			return data.get(key);
		}
		return null;
	}
	
	
	
}
