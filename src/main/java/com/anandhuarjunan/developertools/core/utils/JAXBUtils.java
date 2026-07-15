
package com.anandhuarjunan.developertools.core.utils;

import java.io.File;
import java.io.FileOutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import java.util.logging.Logger;
import java.util.logging.Level;

public class JAXBUtils {

    private static final Logger LOGGER = Logger.getLogger(JAXBUtils.class.getName());

    private JAXBUtils() {}

	@SuppressWarnings("unchecked")
	public static <T> T unmarshal(File file,Class<T> class1){
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
			LOGGER.log(Level.SEVERE, "Error unmarshalling file", e);
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
	    LOGGER.log(Level.SEVERE, "Error marshalling object", e);
	}
	
	}
	
	
}
