/*
 * Copyright 2021 : Anandhu Arjunan
 * Made with Love In India.
 * Email : anandhuka97@gmail.com
╚🍧  🎀  𝐵𝓎  𝒜𝓃𝒶𝓃𝒹𝒽𝓊 𝒜𝓇𝒿𝓊𝓃𝒶𝓃  🎀  🍧 
 */
package com.anzoft.developertools.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class JAXBUtils {

	@SuppressWarnings("unchecked")
	public static <T> T unmarchal(File file,Class<T> class1){
		try{
		    //getting the xml file to read
		    //creating the JAXB context
		    JAXBContext jContext = JAXBContext.newInstance(class1);
		    //creating the unmarshall object
		    Unmarshaller unmarshallerObj = jContext.createUnmarshaller();
		    //calling the unmarshall method
		    return  (T) unmarshallerObj.unmarshal(file);

		}
		catch(RuntimeException e) {
		    throw e;
		}catch(Exception e){
			e.printStackTrace();
		    return null;
		}
	}
	
	public static <T> void marshal(Class<T> class1,T object,File output) {
	try(FileOutputStream	    fileOutputStream  =   new FileOutputStream(output);
){
	    //creating the JAXB context
	    JAXBContext jContext = JAXBContext.newInstance(class1);
	    //creating the marshaller object
	    Marshaller marshallObj = jContext.createMarshaller();
	    //setting the property to show xml format output
	    marshallObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	    //setting the values in POJO class
	    //calling the marshall method
	    marshallObj.marshal(object, fileOutputStream);

	}
	catch(RuntimeException e) {
	    throw e;
	}catch(Exception e) {
	    e.printStackTrace();
	}
	
	}
	
	
}
