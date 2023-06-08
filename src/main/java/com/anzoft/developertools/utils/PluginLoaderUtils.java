/*
 * Copyright 2021 : Anandhu Arjunan
 * Made with Love In India.
 * Email : anandhuka97@gmail.com
╚🍧  🎀  𝐵𝓎  𝒜𝓃𝒶𝓃𝒹𝒽𝓊 𝒜𝓇𝒿𝓊𝓃𝒶𝓃  🎀  🍧 
 */
package com.anzoft.developertools.utils;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.security.AccessController;
import java.security.PrivilegedAction;

import org.zeroturnaround.zip.ZipUtil;

public class PluginLoaderUtils {
    
    private PluginLoaderUtils() {
	
    }
    
    
	   public static void addJarByLibs() {
	        try {
	            File libPath = new File("plugins/");
	            File[] jarFiles = libPath.listFiles(
	                    (dir, name) -> name.endsWith(".dtpl")
	            );
	            if (jarFiles != null) {
	                for (File file : jarFiles) {
	                    addJarClass(file);
	                }
	            }
	        } 
	        catch(RuntimeException e) {
		    throw e;
		}catch (Exception e) {
	        }
	    }

	   
	    @SuppressWarnings({ "unchecked", "rawtypes" })
	    public static void addJarClass(File jarFile) {
	        try {
	        	
	        if(ZipUtil.containsEntry(jarFile, "DTPLUGIN.properties")) {
	            Method method = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
	            AccessController.doPrivileged((PrivilegedAction)()-> {
	    	            method.setAccessible(true); 
	        	    return null;
	        	});
	            URLClassLoader classLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
	            URL url = jarFile.toURI().toURL();
	            method.invoke(classLoader, url);
	        	}
	        	else {
	        		System.err.println(jarFile.getAbsolutePath()+ "is Not a Valid Plugin");
	        	}
	        }catch(RuntimeException e) {
		    throw e;
		} catch (Exception e) {
	        	System.out.println(e);
	        }
	    }
	    
	    @SuppressWarnings({ "unchecked", "rawtypes" })
	    public static void loadJar(File jarFile) throws NoSuchMethodException, MalformedURLException, IllegalAccessException, InvocationTargetException {

	            Method method = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
	            AccessController.doPrivileged((PrivilegedAction)()-> {
	    	            method.setAccessible(true); 
	        	    return null;
	        	});
	            URLClassLoader classLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
	            URL url = jarFile.toURI().toURL();
	            method.invoke(classLoader, url);
	        	
	       
	    }
	   
	    
	    
}
