
package com.anandhuarjunan.developertools.core.utils;

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
