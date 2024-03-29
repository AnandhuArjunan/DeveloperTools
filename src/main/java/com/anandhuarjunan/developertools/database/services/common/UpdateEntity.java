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
public class UpdateEntity<T> extends DBService<T> {

	@Override
	protected ServiceResult<T> process(Session session, Map<String, Object> aruments) throws ServiceException {
		ServiceResult<T> result = new ServiceResult<>();
		session.update(aruments.get(DBService.ENTITY_OBJECT));
		return result;
	}
	public void update(Object entity) {
		ServiceArguments arguments = new ServiceArguments();
		arguments.getArgs().put(DBService.ENTITY_OBJECT, entity);
		try {
			run(arguments) ;
		} catch (ServiceException e) {
			//new ExceptionHandler().handleException(e);

		}
	}
}
