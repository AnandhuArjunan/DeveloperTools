/*
 * 
 */
package com.anandhuarjunan.developertools.core.app.config;

import java.util.List;

import com.anandhuarjunan.developertools.core.cache.keyvalue.KeyValueConfigurationImpl;
import com.anandhuarjunan.developertools.core.database.persistables.InternalTools;
import com.anandhuarjunan.developertools.core.database.services.common.GetAllEntityService;
import com.anandhuarjunan.developertools.core.exceptions.InternalError;
import com.anandhuarjunan.developertools.core.exceptions.ServiceException;
import com.anandhuarjunan.developertools.core.exceptions.ValidateError;

public class FXIDClassMapping extends KeyValueConfigurationImpl<String, String> {
	
	

public FXIDClassMapping() throws ValidateError, InternalError {
super();
}

@Override
public void loadConfiguration() throws InternalError {
	
	
	GetAllEntityService<InternalTools> allInternalTools = new GetAllEntityService<InternalTools>();
	try {
		List<InternalTools> internalTools =  allInternalTools.getAllRecords(InternalTools.class);
		for(InternalTools tool : internalTools) {
			if(null != tool) {
				addConfiguration(tool.getTool().getId(),tool.getClassName());
			}
		}
		
	} catch (ServiceException e) {
		throw new InternalError("Failed to Get Tools Information");
	}
	
	
	
		
}


		
	}


