/*
 * 
 */
package com.anandhuarjunan.developertools.core.cache.keyvalue;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.anandhuarjunan.developertools.core.app.ActionExecutor;
import com.anandhuarjunan.developertools.core.app.Validator;
import com.anandhuarjunan.developertools.core.exceptions.InternalError;
import com.anandhuarjunan.developertools.core.exceptions.ValidateError;

public abstract class KeyValueConfigurationImpl<K,V> implements KeyValueConfiguration<K, V> {
	
	private Map<K, V> config = null;
	protected  KeyValueConfigurationImpl() throws InternalError, ValidateError {
		config = Collections.synchronizedMap(new HashMap<K,V>());
		loadConfiguration();
		if(this instanceof Validator validator) {
			validator.validate();
		}
		if(this instanceof ActionExecutor actionexecuter) {
			actionexecuter.executeAction();
		}
	}

	@Override
	public void addConfiguration(K key, V value) {
		config.computeIfAbsent(key, k->value);
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
