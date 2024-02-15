/*
 * 
 */
package com.anandhuarjunan.developertools.database.services.common;

import java.util.Map;

import org.hibernate.Session;


import com.anandhuarjunan.developertools.database.DBService;
import com.anandhuarjunan.developertools.database.ServiceArguments;
import com.anandhuarjunan.developertools.database.ServiceResult;
import com.anandhuarjunan.developertools.exceptions.ServiceException;
public class PersistEntity<T> extends DBService<T> {

	@Override
	protected ServiceResult<T> process(Session session, Map<String, Object> aruments) throws ServiceException {
		ServiceResult<T> result = new ServiceResult<>();
		session.persist(aruments.get(DBService.ENTITY_OBJECT));
		return result;
	}

	public void persist(Object entity) throws ServiceException {
	
			run(makeArgument(entity)) ;
	
	}
	
	public void persist(Object entity,Session session) throws ServiceException {
		ServiceArguments arguments = new ServiceArguments();
		arguments.getArgs().put(DBService.ENTITY_OBJECT, entity);
	
			run(makeArgument(entity),session) ;
		
	}
	
	
	private ServiceArguments makeArgument(Object entity) {
		ServiceArguments arguments = new ServiceArguments();
		arguments.getArgs().put(DBService.ENTITY_OBJECT, entity);
		return arguments;
	}
}
