/*
 * Copyright 2021 : Anandhu Arjunan
 * Made with Love In India.
 * Email : anandhuka97@gmail.com
╚🍧  🎀  𝐵𝓎  𝒜𝓃𝒶𝓃𝒹𝒽𝓊 𝒜𝓇𝒿𝓊𝓃𝒶𝓃  🎀  🍧 
 */
package com.anzoft.developertools.database.services;

import java.util.Map;

import org.hibernate.Session;

import com.anzoft.developertools.database.persistables.ToolBookmark;
import com.anzoft.developertools.database.persistables.Tools;
import com.anzoft.developertools.exceptions.ServiceException;

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
