package com.anandhuarjunan.developertools.utils;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;

public class JarUtils {
	  @SuppressWarnings({ "unchecked", "rawtypes" })
	    public static ClassLoader loadJar(File jarFile) throws NoSuchMethodException, MalformedURLException, IllegalAccessException, InvocationTargetException {

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
	    	
	    	return classLoader;
	    }
}
