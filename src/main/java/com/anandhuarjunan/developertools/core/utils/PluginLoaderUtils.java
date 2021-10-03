
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

	    	try(IndependentClassLoader classLoader = new IndependentClassLoader(new URL[0], PluginLoaderUtils.class.getClassLoader())){
	    		 URL url = jarFile.toURI().toURL();
		    	    classLoader.addURL(url);
	    	} catch (IOException e) {
				e.printStackTrace();
			}
	        	
	    	
	    	    
	    }
	   
	    
	    
}
