
package com.anandhuarjunan.developertools.core.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import java.util.logging.Logger;

public class ZipUtils {

    private static final Logger LOGGER = Logger.getLogger(ZipUtils.class.getName());

    private ZipUtils() {}

	 public static void zipFolder(File srcFolder, File destZipFile) throws Exception {
	        try (FileOutputStream fileWriter = new FileOutputStream(destZipFile);
	                ZipOutputStream zip = new ZipOutputStream(fileWriter)) {

	            addFolderToZip(srcFolder, srcFolder, zip);
	        }
	    }

	    private static void addFileToZip(File rootPath, File srcFile, ZipOutputStream zip) throws Exception {

	        if (srcFile.isDirectory()) {
	            addFolderToZip(rootPath, srcFile, zip);
	        } else {
	            byte[] buf = new byte[1024];
	            int len;
	            try (FileInputStream in = new FileInputStream(srcFile)) {
	                String name = srcFile.getPath();
	                name = name.replace(rootPath.getPath(), "");
	                LOGGER.info("Zip " + srcFile + "\n to " + name);
	                zip.putNextEntry(new ZipEntry(name));
	                while ((len = in.read(buf)) > 0) {
	                    zip.write(buf, 0, len);
	                }
	            }
	        }
	    }

	    private static void addFolderToZip(File rootPath, File srcFolder, ZipOutputStream zip) throws Exception {
	        File[] files = srcFolder.listFiles();
	        if (files != null) {
	            for (File fileName : files) {
	                addFileToZip(rootPath, fileName, zip);
	            }
	        }
	    }
	}
