/*
 * 
 */
package com.anandhuarjunan.developertools.core.cache;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.configuration2.CompositeConfiguration;
public class ConfigCache 
{ 
	private static ConfigCache cache = null; 
	private Map<String,CompositeConfiguration> conf = null;
	private ConfigCache() 
	{ 
		conf = new HashMap<>();
	} 

	public static synchronized ConfigCache getInstance() 
	{ 
		if (cache == null) 
			cache = new ConfigCache(); 

		return cache; 
	} 
	
	
	
	public CompositeConfiguration getConfig(String key) {
		return conf.get(key);
	}
	public void addConfiguration(String key,CompositeConfiguration configuration) {
		conf.put(key, configuration);
	}
	public boolean containsConfiguration(String key) {
		return conf.containsKey(key);
		}
} 


