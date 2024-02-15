/*
 * 
 */
package com.anandhuarjunan.developertools.database.services.common;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;


import com.anandhuarjunan.developertools.database.DBService;
import com.anandhuarjunan.developertools.database.ServiceArguments;
import com.anandhuarjunan.developertools.database.ServiceResult;
import com.anandhuarjunan.developertools.exceptions.ServiceException;
public class FindEntityByFieldsService<T> extends DBService<T> {

	@SuppressWarnings("unchecked")
	@Override
	protected ServiceResult<T> process(Session session, Map<String, Object> aruments) throws ServiceException {
		ServiceResult<T> result = new ServiceResult<>();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<T> cr = cb.createQuery((Class<T>) aruments.get(DBService.ENTITY_CLASS));
		Root<T> root = cr.from((Class<T>) aruments.get(DBService.ENTITY_CLASS));
		cr.select(root);
		Map<String,Object> dbArgs = (Map<String, Object>) aruments.get(DBService.DB_ARGS);
		
		 Predicate[] criterias = new Predicate[dbArgs.size()];
		 Iterator<Entry<String,Object>> it = dbArgs.entrySet().iterator();
		 int i = 0;

		 while(it.hasNext()) {
			 Entry<String,Object> entry = it.next();
			 criterias[i]=cb.equal(root.get(entry.getKey()),entry.getValue());
			 i++;
		 }
			cr.select(root).where(cb.and(criterias));
		
		Query query = session.createQuery(cr);
		List<?> items = query.getResultList();
		result.setResult(null != items && !items.isEmpty()?(T)items.get(0):null);
		return result;
	}

	public ServiceResult<T> findEntityByFields(Class<?> aruments,Map<String,Object> map) throws ServiceException{
		ServiceArguments arguments=new ServiceArguments();
		arguments.getArgs().put(ENTITY_CLASS, aruments);
		arguments.getArgs().put(DB_ARGS, map);
		return run(arguments);
		
	}
	
	
}
