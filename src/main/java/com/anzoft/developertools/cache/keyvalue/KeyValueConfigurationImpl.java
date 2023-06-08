/*
 * Copyright 2021 : Anandhu Arjunan
 * Made with Love In India.
 * Email : anandhuka97@gmail.com
╚🍧  🎀  𝐵𝓎  𝒜𝓃𝒶𝓃𝒹𝒽𝓊 𝒜𝓇𝒿𝓊𝓃𝒶𝓃  🎀  🍧 
 */
package com.anzoft.developertools.cache.keyvalue;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.anzoft.developertools.app.ActionExecutor;
import com.anzoft.developertools.app.Validator;
import com.anzoft.developertools.exceptions.InternalError;
import com.anzoft.developertools.exceptions.ValidateError;

public abstract class KeyValueConfigurationImpl<K,V> implements KeyValueConfiguration<K, V> {
	
	private Map<K, V> config = null;
	public  KeyValueConfigurationImpl() throws InternalError, ValidateError {
		config = Collections.synchronizedMap(new HashMap<K,V>());
		loadConfiguration();
		if(this instanceof Validator) {
			((Validator)this).validate();
		}
		if(this instanceof ActionExecutor) {
			((ActionExecutor)this).executeAction();
		}
	}

	@Override
	public void addConfiguration(K key, V value) {
		if(!config.containsKey(key)) {
			config.put(key, value);
		}
	}

	@Override
	public V getConfiguration(K key) {
		return config.get(key);
	}
	
	@Override
	public V getConfiguration(K key,V defaultValue) {
		return null == getConfiguration(key)?defaultValue:getConfiguration(key);
	}
	
	public Map<K, V>  getAllConfiguration(){
		return config;
	}

	@Override
	public void modifyConfiguration(K key, V value) {
		config.replace(key, value);
	}

	@Override
	public void removeConfiguration(K key) {
		config.remove(key);
	}

	@Override
	public boolean isPresent(K key) {
		
		return config.containsKey(key);
	}

	@Override
	public void clear() {
		config.clear();
		
	}

	@Override
	public void reloadConfiguration() throws InternalError {
		clear();
		loadConfiguration();
		
	}

}
