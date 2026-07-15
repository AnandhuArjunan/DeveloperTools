
package com.anandhuarjunan.developertools.core.utils;

import java.io.File;

public class Util {

    private Util() {}

	public static File getResourceFile(String fileName) {
		ClassLoader classLoader = ClassLoader.getSystemClassLoader();
		java.net.URL resource = classLoader.getResource(fileName);
		if (resource == null) {
		    throw new IllegalArgumentException("Resource not found: " + fileName);
		}
		return new File(resource.getFile());
	}
	


}
