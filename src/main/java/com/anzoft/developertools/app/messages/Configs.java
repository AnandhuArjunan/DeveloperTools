/*
 * Copyright 2021 : Anandhu Arjunan
 * Made with Love In India.
 * Email : anandhuka97@gmail.com
╚🍧  🎀  𝐵𝓎  𝒜𝓃𝒶𝓃𝒹𝒽𝓊 𝒜𝓇𝒿𝓊𝓃𝒶𝓃  🎀  🍧 
 */
package com.anzoft.developertools.app.messages;

import java.util.HashMap;
import java.util.Map;

public abstract class Configs<K,V> {
	private Map<K,V> config = new HashMap<>();
	
	public void addConfiguration(K k,V v) {
		config.put(k, v);
	}
	
	public V getConfiguration(K k) {
		 V v = null;
		if(config.containsKey(k)) {
			return config.get(k);
		}
		return v;
	}	
	

}
