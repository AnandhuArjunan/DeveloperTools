
package com.anandhuarjunan.developertools.utils;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
	 
	    private static final SessionFactory sessionFactory;
	     
	    static{
	        try{
	            sessionFactory =new Configuration().configure().buildSessionFactory();
	 
	        }catch (Exception ex) {
	            System.err.println("Session Factory could not be created." + ex);
	            throw new ExceptionInInitializerError(ex);
	        }   
	    }
	     
	    public static SessionFactory getSessionFactory() {
	        return sessionFactory;
	    }
	    
	    public static <T> List<T> loadAllData(Class<T> type, Session session) {
	        CriteriaBuilder builder = session.getCriteriaBuilder();
	        CriteriaQuery<T> criteria = builder.createQuery(type);
	        criteria.from(type);
	        return session.createQuery(criteria).getResultList();
	      }
	 
}
