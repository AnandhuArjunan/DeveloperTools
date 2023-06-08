/*
 * Copyright 2021 : Anandhu Arjunan
 * Made with Love In India.
 * Email : anandhuka97@gmail.com
╚🍧  🎀  𝐵𝓎  𝒜𝓃𝒶𝓃𝒹𝒽𝓊 𝒜𝓇𝒿𝓊𝓃𝒶𝓃  🎀  🍧 
 */
package com.anzoft.developertools.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializationUtils {

	private SerializationUtils() {}
	public static void serialize(String path,Object toWrite) throws IOException {

		try (FileOutputStream fileOut = new FileOutputStream(path);
				ObjectOutputStream  out = new ObjectOutputStream(fileOut);){
		         out.writeObject(toWrite);
		}
	}
	
    public static Object deserialize(String path) throws ClassNotFoundException, IOException {
    	try (
    	 FileInputStream fileIn = new FileInputStream(path);
         ObjectInputStream in = new ObjectInputStream(fileIn);){
            return  in.readObject();

    	}
	}
}
