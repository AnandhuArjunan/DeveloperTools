/*
 * Copyright 2021 : Anandhu Arjunan
 * Made with Love In India.
 * Email : anandhuka97@gmail.com
╚🍧  🎀  𝐵𝓎  𝒜𝓃𝒶𝓃𝒹𝒽𝓊 𝒜𝓇𝒿𝓊𝓃𝒶𝓃  🎀  🍧 
 */
package com.anzoft.developertools.app.config;

import java.util.List;

import com.anzoft.developertools.cache.keyvalue.KeyValueConfigurationImpl;
import com.anzoft.developertools.database.persistables.InternalTools;
import com.anzoft.developertools.database.services.common.GetAllEntityService;
import com.anzoft.developertools.exceptions.InternalError;
import com.anzoft.developertools.exceptions.ServiceException;
import com.anzoft.developertools.exceptions.ValidateError;

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


