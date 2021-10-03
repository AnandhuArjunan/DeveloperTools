/*
 * 
 */
package com.anandhuarjunan.developertools.core.database.services;

import java.util.Map;

import org.hibernate.Session;

import com.anandhuarjunan.developertools.core.database.persistables.ToolBookmark;
import com.anandhuarjunan.developertools.core.database.persistables.Tools;
import com.anandhuarjunan.developertools.core.exceptions.ServiceException;

public class LogToolBookmarkService extends DBService<ToolBookmark> {

	@Override
	protected ServiceResult<ToolBookmark> process(Session session, Map<String, Object> aruments)
			throws ServiceException {
		
		
		ToolBookmark activityHistory= new ToolBookmark();
		Tools tools = session.find(Tools.class, aruments.get(DBService.PRIMARY_KEY));
		if(tools.isVisible()) {
			activityHistory.setTools(tools);
			activityHistory.setToolPosition((int)aruments.get("POSITION"));
			session.persist(activityHistory);
		}
		return new ServiceResult<>();
		
	}

}
