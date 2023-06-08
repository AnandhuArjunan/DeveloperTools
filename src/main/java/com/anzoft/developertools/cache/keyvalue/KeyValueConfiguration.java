/*
 * Copyright 2021 : Anandhu Arjunan
 * Made with Love In India.
 * Email : anandhuka97@gmail.com
╚🍧  🎀  𝐵𝓎  𝒜𝓃𝒶𝓃𝒹𝒽𝓊 𝒜𝓇𝒿𝓊𝓃𝒶𝓃  🎀  🍧 
 */
package com.anzoft.developertools.cache.keyvalue;

import com.anzoft.developertools.exceptions.InternalError;

public interface KeyValueConfiguration<K,V> {

	void  addConfiguration(K key,V value);
	
	V getConfiguration(K key);
	
	V getConfiguration(K key,V defaultValue);
	
	void modifyConfiguration(K key,V value);
	
	void removeConfiguration(K key);
	
	boolean isPresent(K key);
	
	void clear();
	
	void loadConfiguration() throws InternalError;
	
	void reloadConfiguration() throws InternalError;
	
	
	
	
	
}
