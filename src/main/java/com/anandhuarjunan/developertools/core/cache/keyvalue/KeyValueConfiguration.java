/*
 * 
 */
package com.anandhuarjunan.developertools.core.cache.keyvalue;

import com.anandhuarjunan.developertools.core.exceptions.InternalError;

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
