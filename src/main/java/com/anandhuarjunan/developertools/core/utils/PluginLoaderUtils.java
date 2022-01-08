
package com.anandhuarjunan.developertools.core.utils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.security.AccessController;
import java.security.PrivilegedAction;

import org.zeroturnaround.zip.ZipUtil;

import com.anandhuarjunan.developertools.core.utils.classloader.IndependentClassLoader;

public class PluginLoaderUtils {
    
    private PluginLoaderUtils() {
	
    }
    
    
	  
	    
	    @SuppressWarnings({ "unchecked", "rawtypes" })
	    public static void loadJar(File jarFile) throws NoSuchMethodException, MalformedURLException, IllegalAccessException, InvocationTargetException {

	    	ClassLoader classLoader = ClassLoader.getSystemClassLoader();
	    	try {
	    	    Method method = classLoader.getClass().getDeclaredMethod("addURL", URL.class);
	    	    method.setAccessible(true);
	    	    method.invoke(classLoader, jarFile.toURI().toURL());
	    	} catch (NoSuchMethodException e) {
	    	    Method method = classLoader.getClass()
	    	            .getDeclaredMethod("appendToClassPathForInstrumentation", String.class);
	    	    method.setAccessible(true);
	    	    method.invoke(classLoader, jarFile.getAbsolutePath());
	    	}
	    	
	    	    
	    }
	   
	    
	    
}
