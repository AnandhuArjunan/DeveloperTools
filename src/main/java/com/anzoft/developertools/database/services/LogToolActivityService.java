/*
 * Copyright 2021 : Anandhu Arjunan
 * Made with Love In India.
 * Email : anandhuka97@gmail.com
╚🍧  🎀  𝐵𝓎  𝒜𝓃𝒶𝓃𝒹𝒽𝓊 𝒜𝓇𝒿𝓊𝓃𝒶𝓃  🎀  🍧 
 */
package com.anzoft.developertools.database.services;

import java.util.Date;
import java.util.Map;

import org.hibernate.Session;

import com.anzoft.developertools.database.persistables.ToolActivityHistory;
import com.anzoft.developertools.database.persistables.Tools;
import com.anzoft.developertools.exceptions.ServiceException;

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
