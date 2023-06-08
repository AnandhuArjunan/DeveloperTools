/*
 * Copyright 2021 : Anandhu Arjunan
 * Made with Love In India.
 * Email : anandhuka97@gmail.com
╚🍧  🎀  𝐵𝓎  𝒜𝓃𝒶𝓃𝒹𝒽𝓊 𝒜𝓇𝒿𝓊𝓃𝒶𝓃  🎀  🍧 
 */
package com.anzoft.developertools.controller.toolloaders;

import com.anzoft.developertools.database.persistables.Tools;
import com.anzoft.developertools.exceptions.ToolError;

public interface ToolLoader {

	
	
	
	public void loadTool(Tools tool) throws ToolError;
}
