/*
 * 
 */
package com.anandhuarjunan.developertools.core.database.services;

import java.util.Date;
import java.util.Map;

import org.hibernate.Session;

import com.anandhuarjunan.developertools.core.database.persistables.ToolActivityHistory;
import com.anandhuarjunan.developertools.core.database.persistables.Tools;
import com.anandhuarjunan.developertools.core.exceptions.ServiceException;

public class LogToolActivityService extends DBService<ToolActivityHistory> {

	@Override
	protected ServiceResult<ToolActivityHistory> process(Session session, Map<String, Object> aruments)
			throws ServiceException {
		
		
		ToolActivityHistory activityHistory= new ToolActivityHistory();
		Tools tools = session.find(Tools.class, aruments.get(DBService.PRIMARY_KEY));
		if(tools.isVisible()) {
			activityHistory.setTools(tools);
			activityHistory.setToolAcivityDate(new Date());
			session.persist(activityHistory);
		}
		return new ServiceResult<>();
		
	}

}
