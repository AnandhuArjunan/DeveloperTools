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
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipUtils {

	
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
	                System.out.println("Zip " + srcFile + "\n to " + name);
	                zip.putNextEntry(new ZipEntry(name));
	                while ((len = in.read(buf)) > 0) {
	                    zip.write(buf, 0, len);
	                }
	            }
	        }
	    }

	    private static void addFolderToZip(File rootPath, File srcFolder, ZipOutputStream zip) throws Exception {
	        for (File fileName : srcFolder.listFiles()) {
	            addFileToZip(rootPath, fileName, zip);
	        }
	    }
	}
