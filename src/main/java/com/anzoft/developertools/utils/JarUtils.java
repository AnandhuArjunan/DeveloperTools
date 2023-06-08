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

import org.apache.commons.compress.archivers.jar.JarArchiveEntry;
import org.apache.commons.compress.archivers.jar.JarArchiveInputStream;
import org.apache.commons.compress.utils.IOUtils;

public class JarUtils {
	public static void decompress(File in, File destination) throws IOException {
		

        try (JarArchiveInputStream jin = new JarArchiveInputStream(new FileInputStream(in))){
            JarArchiveEntry entry;
            while ((entry = jin.getNextJarEntry()) != null) {
                if (entry.isDirectory()){
                    continue;
                }
                File curfile = new File(destination, entry.getName());
                File parent = curfile.getParentFile();
                if (!parent.exists()) {
                    if (!parent.mkdirs()){
                        throw new RuntimeException("could not create directory: " + parent.getPath());
                    }
                }
                IOUtils.copy(jin, new FileOutputStream(curfile));
            }
        }
    }

	
}
