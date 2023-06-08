/*
 * Copyright 2021 : Anandhu Arjunan
 * Made with Love In India.
 * Email : anandhuka97@gmail.com
╚🍧  🎀  𝐵𝓎  𝒜𝓃𝒶𝓃𝒹𝒽𝓊 𝒜𝓇𝒿𝓊𝓃𝒶𝓃  🎀  🍧 
 */
package com.anzoft.developertools.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;



public class PropertyUtils {


	public static Properties loadProperty(File file) throws  IOException  {
	    try(FileInputStream fileInputStream = new FileInputStream(file);
){
		Properties property = new Properties();
		property.load(fileInputStream);
		return property;

	    }
	}
	public static void saveProperties(Properties properties,File file) throws IOException
	    {
	    try(FileOutputStream fr = new FileOutputStream(file);
) {
	        properties.store(fr, "Properties");
	    }
	    }
	
	public static  Map<String,String> convertToMap(Properties properties){
		Map<String,String> map  = new HashMap<>();
		for (final String name: properties.stringPropertyNames()) {
		    map.put(name, properties.getProperty(name));}
		return map;
	}
	
	public static  Map<String,String> convertToMap(File file) throws IOException{
		Properties properties = loadProperty(file);
		Map<String,String> map  = new HashMap<>();
		for (final String name: properties.stringPropertyNames()) {
		    map.put(name, properties.getProperty(name));}
		return map;
	}
	public static Properties parsePropertiesString(String s) throws IOException {
	    // grr at load() returning void rather than the Properties object
	    // so this takes 3 lines instead of "return new Properties().load(...);"
	    final Properties p = new Properties();
	    p.load(new StringReader(s));
	    return p;
	}
}
