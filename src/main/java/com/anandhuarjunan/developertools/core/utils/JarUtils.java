
package com.anandhuarjunan.developertools.core.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.compress.archivers.jar.JarArchiveEntry;
import org.apache.commons.compress.archivers.jar.JarArchiveInputStream;
import org.apache.commons.compress.utils.IOUtils;

public class JarUtils {
	
	private JarUtils() {}
	
	public static void decompress(File in, File destination) throws IOException {
		

        String destCanonicalPath = destination.getCanonicalPath();

        try (JarArchiveInputStream jin = new JarArchiveInputStream(new FileInputStream(in))){
            JarArchiveEntry entry;
            while ((entry = jin.getNextJarEntry()) != null) {
                if (entry.isDirectory()){
                    continue;
                }
                File curfile = new File(destination, entry.getName());
                if (!curfile.getCanonicalPath().startsWith(destCanonicalPath + File.separator)) {
                    throw new IOException("Entry is outside of the target dir: " + entry.getName());
                }
                File parent = curfile.getParentFile();
                if (!parent.exists()) {
                    if (!parent.mkdirs()){
                        throw new RuntimeException("could not create directory: " + parent.getPath());
                    }
                }
                try (FileOutputStream fos = new FileOutputStream(curfile)) {
                    IOUtils.copy(jin, fos);
                }
            }
        }
    }

	
}
