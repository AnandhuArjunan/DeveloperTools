
package com.anandhuarjunan.developertools.core.utils;

import java.io.File;

public class Util {

	

	public static File getResourceFile(String fileName) {
		ClassLoader classLoader = ClassLoader.getSystemClassLoader();
		return new File(classLoader.getResource(fileName).getFile());
	}
	


}
