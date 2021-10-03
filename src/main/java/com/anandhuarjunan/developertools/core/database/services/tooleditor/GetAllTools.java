/*
 * 
 */
package com.anandhuarjunan.developertools.core.database.services.tooleditor;

import java.util.List;
import java.util.Map;

import org.hibernate.Session;

import com.anandhuarjunan.developertools.core.constants.CommonConstant;
import com.anandhuarjunan.developertools.core.database.persistables.Tools;
import com.anandhuarjunan.developertools.core.database.services.ServiceResult;
import com.anandhuarjunan.developertools.core.database.services.common.GetAllEntityService;
import com.anandhuarjunan.developertools.core.exceptions.ServiceException;

public class GetAllTools extends GetAllEntityService<List<Tools>> {

	@Override
	public ServiceResult<List<Tools>> process(Session session, Map<String, Object> aruments)
			throws ServiceException {
		aruments.put(CommonConstant.CLASS_NAME, Tools.class);
		return super.process(session, aruments);
	}
	
}
