/*
 * 
 */
package com.anandhuarjunan.developertools.core.cache;

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
