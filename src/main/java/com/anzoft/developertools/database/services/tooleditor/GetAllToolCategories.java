/*
 * Copyright 2021 : Anandhu Arjunan
 * Made with Love In India.
 * Email : anandhuka97@gmail.com
╚🍧  🎀  𝐵𝓎  𝒜𝓃𝒶𝓃𝒹𝒽𝓊 𝒜𝓇𝒿𝓊𝓃𝒶𝓃  🎀  🍧 
 */
package com.anzoft.developertools.database.services.tooleditor;

import java.util.List;
import java.util.Map;

import org.hibernate.Session;

import com.anzoft.developertools.constants.CommonConstant;
import com.anzoft.developertools.database.persistables.Category;
import com.anzoft.developertools.database.services.ServiceResult;
import com.anzoft.developertools.database.services.common.GetAllEntityService;
import com.anzoft.developertools.exceptions.ServiceException;

public class GetAllToolCategories extends GetAllEntityService<List<Category>> {

	@Override
	public ServiceResult<List<Category>> process(Session session, Map<String, Object> aruments)
			throws ServiceException {
		aruments.put(CommonConstant.CLASS_NAME, Category.class);
		return super.process(session, aruments);
	}
}