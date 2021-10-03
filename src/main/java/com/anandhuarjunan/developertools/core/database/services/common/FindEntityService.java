/*
 * 
 */
package com.anandhuarjunan.developertools.core.database.services.common;

import java.util.Map;

import org.hibernate.Session;

import com.anandhuarjunan.developertools.core.database.services.DBService;
import com.anandhuarjunan.developertools.core.database.services.ServiceArguments;
import com.anandhuarjunan.developertools.core.database.services.ServiceResult;
import com.anandhuarjunan.developertools.core.exceptions.ServiceException;

public class FindEntityService<T> extends DBService<T> {

	@SuppressWarnings("unchecked")
	@Override
	protected ServiceResult<T> process(Session session, Map<String, Object> aruments) throws ServiceException {
		ServiceResult<T> result = new ServiceResult<>();
		result.setResult(session.find((Class<T>) aruments.get(DBService.ENTITY_CLASS), aruments.get(DBService.PRIMARY_KEY)));
		return result;
	}

	public ServiceResult<T> findEntity(Class<?> aruments,Object id) throws ServiceException{
		ServiceArguments arguments=new ServiceArguments();
		arguments.getArgs().put(ENTITY_CLASS, aruments);
		arguments.getArgs().put(PRIMARY_KEY, id);
		return run(arguments);
		
	}
	
}
