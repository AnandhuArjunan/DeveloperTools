/*
 * Copyright 2021 : Anandhu Arjunan
 * Made with Love In India.
 * Email : anandhuka97@gmail.com
╚🍧  🎀  𝐵𝓎  𝒜𝓃𝒶𝓃𝒹𝒽𝓊 𝒜𝓇𝒿𝓊𝓃𝒶𝓃  🎀  🍧 
 */
package com.anzoft.developertools.database.services.common;

import java.util.List;
import java.util.Map;

import org.hibernate.Session;

import com.anzoft.developertools.constants.CommonConstant;
import com.anzoft.developertools.database.services.DBService;
import com.anzoft.developertools.database.services.ServiceArguments;
import com.anzoft.developertools.database.services.ServiceResult;
import com.anzoft.developertools.exceptions.ServiceException;
import com.anzoft.developertools.utils.HibernateUtils;

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
