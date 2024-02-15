/*
 * 
 */
package com.anandhuarjunan.developertools.database.services.common;

import java.util.List;
import java.util.Map;

import org.hibernate.Session;

import com.anandhuarjunan.developertools.constants.CommonConstant;
import com.anandhuarjunan.developertools.database.DBService;
import com.anandhuarjunan.developertools.database.ServiceArguments;
import com.anandhuarjunan.developertools.database.ServiceResult;
import com.anandhuarjunan.developertools.exceptions.ServiceException;
import com.anandhuarjunan.developertools.utils.HibernateUtils;
public class GetAllEntityService<T> extends DBService<T> {

	@SuppressWarnings("unchecked")
	@Override
	protected ServiceResult<T> process(Session session, Map<String, Object> aruments) throws ServiceException {
		if(null != aruments && aruments.containsKey(CommonConstant.CLASS_NAME)) {
			ServiceResult<List<T>> result = new ServiceResult<>();
			result.setResult((List<T>) HibernateUtils.loadAllData((Class<?>)aruments.get(CommonConstant.CLASS_NAME), session));
			return (ServiceResult<T>) result;
		}
		return null;
	}
	@SuppressWarnings("unchecked")
	public List<T> getAllRecords(Class<T> class1)  throws ServiceException{
		ServiceArguments arguments = new ServiceArguments();
		arguments.getArgs().put(CommonConstant.CLASS_NAME, class1);
		ServiceResult<T> result =  run(arguments);
		return (List<T>) result.getResult();
	}

}
