/*
 * 
 */
package com.anandhuarjunan.developertools.core.database.services;

import java.time.Duration;
import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.anandhuarjunan.developertools.core.exceptions.ServiceException;
import com.anandhuarjunan.developertools.core.utils.HibernateUtils;

public abstract class DBService<T> {
	
	public static final String ENTITY_CLASS="ET";
	public static final String ENTITY_OBJECT="EB";

	public static final String PRIMARY_KEY="PK";
	public static final String DB_ARGS="DA";
	public static final String HQL_QUERY="HQ";

	
	Session session = null;
	Transaction tx = null;

	public ServiceResult<T> run(ServiceArguments args) throws ServiceException{
		try {
			Instant first = Instant.now();
			session = HibernateUtils.getSessionFactory().openSession();
			tx = session.beginTransaction();
			ServiceResult<T> result = process(session, args.getArgs());
			tx.commit();
			afterProcess();
			Instant last = Instant.now();
			result.setTimeTaken(Duration.between(first, last));
			return result;
		}
		catch(RuntimeException e) {
		    throw e;
		}
		catch(Exception e) {
			tx.rollback();
			e.printStackTrace();
			throw new ServiceException(this.getClass().getName()+" has been failed due to Internal Error .");
		} 
	}
	
	public ServiceResult<T> run(ServiceArguments args,Session session) throws ServiceException{
		try {
			Instant first = Instant.now();
			ServiceResult<T> result = process(session, args.getArgs());
			Instant last = Instant.now();
			result.setTimeTaken(Duration.between(first, last));
			return result;
		}
		catch(RuntimeException e) {
		    throw e;
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new ServiceException(this.getClass().getName()+" has been failed due to Internal Error .");
		} 
	}
	
	
	
	
	protected void afterProcess() {
		
		if(null != session) {
			session.close();
		}
	}
	
	
	protected abstract ServiceResult<T> process(Session session,Map<String,Object> aruments) throws ServiceException;


}
