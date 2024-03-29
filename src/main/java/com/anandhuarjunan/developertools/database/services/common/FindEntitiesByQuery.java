/*
 * 
 */
package com.anandhuarjunan.developertools.database.services.common;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.anandhuarjunan.developertools.database.DBService;
import com.anandhuarjunan.developertools.database.ServiceArguments;
import com.anandhuarjunan.developertools.database.ServiceResult;
import com.anandhuarjunan.developertools.exceptions.ServiceException;
public class FindEntitiesByQuery<T> extends DBService<List<T>> {

	@SuppressWarnings({ "unchecked" })
	@Override
	protected ServiceResult<List<T>> process(Session session, Map<String, Object> aruments) throws ServiceException {
		String hqlQuery = (String) aruments.get(DBService.HQL_QUERY);
		Query<T> query = session.createQuery(hqlQuery);
		aruments.remove(DBService.HQL_QUERY);
		 Iterator<Entry<String,Object>> it = aruments.entrySet().iterator();
		 while(it.hasNext()) {
			 Entry<String,Object> entry = it.next();
			 query.setParameter(entry.getKey(), entry.getValue());
		 }
		List<T> results = query.list();
		ServiceResult<List<T>>  result = new ServiceResult<>();
		result.setResult(results);
		return result;
	}

	@SuppressWarnings("unchecked")
	public T executeHQL(String hqlQuery,Map<String,Object> args) {
		Map<String,Object> arguments =  null !=args && !args.isEmpty()?args:new HashMap<>();
		arguments.put(DBService.HQL_QUERY, hqlQuery);
		ServiceArguments serviceArgs = new ServiceArguments();
		serviceArgs.setArgs(arguments);
		ServiceResult<T> result = null;
		try {
			result = (ServiceResult<T>) run(serviceArgs);
			return result.getResult();
		} catch (ServiceException e) {
		}
		return null;
	}	
	
	
}
