/*
 * Copyright 2021 : Anandhu Arjunan
 * Made with Love In India.
 * Email : anandhuka97@gmail.com
╚🍧  🎀  𝐵𝓎  𝒜𝓃𝒶𝓃𝒹𝒽𝓊 𝒜𝓇𝒿𝓊𝓃𝒶𝓃  🎀  🍧 
 */
package com.anzoft.developertools.database.services.common;

import java.util.Map;

import org.hibernate.Session;

import com.anzoft.developertools.database.services.DBService;
import com.anzoft.developertools.database.services.ServiceArguments;
import com.anzoft.developertools.database.services.ServiceResult;
import com.anzoft.developertools.exceptions.ServiceException;

public class DeleteEntity<T> extends DBService<T>{

	@Override
	protected ServiceResult<T> process(Session session, Map<String, Object> aruments) throws ServiceException {
		ServiceResult<T> result = new ServiceResult<>();
		session.delete(aruments.get(DBService.ENTITY_OBJECT));
		return result;
	}
	
	
	public void delete(Object entity) throws ServiceException{
		ServiceArguments arguments = new ServiceArguments();
		arguments.getArgs().put(DBService.ENTITY_OBJECT, entity);
		run(arguments) ; 
	}

}