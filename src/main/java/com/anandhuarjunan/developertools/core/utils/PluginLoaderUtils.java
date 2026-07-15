
package com.anandhuarjunan.developertools.core.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.File;


public class PluginLoaderUtils {
    
    private PluginLoaderUtils() {
	
    }
    
    
	  
	    
	    public static void loadJar(File jarFile) throws NoSuchMethodException, MalformedURLException, IllegalAccessException, InvocationTargetException {

	    	ClassLoader classLoader = ClassLoader.getSystemClassLoader();
	    	try {
	    	    Method method = classLoader.getClass().getDeclaredMethod("addURL", URL.class);
	    	    // Security warning: breaking encapsulation to inject URL into ClassLoader
	    	    method.setAccessible(true);
	    	    method.invoke(classLoader, jarFile.toURI().toURL());
	    	} catch (NoSuchMethodException e) {
	    	    Method method = classLoader.getClass()
	    	            .getDeclaredMethod("appendToClassPathForInstrumentation", String.class);
	    	    // Security warning: breaking encapsulation to inject path into ClassLoader
	    	    method.setAccessible(true);
	    	    method.invoke(classLoader, jarFile.getAbsolutePath());
	    	}
	    	
	    	    
	    }
	   
	    
	    
}
