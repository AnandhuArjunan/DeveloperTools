/*
 * 
 */
package com.anandhuarjunan.developertools.core.controller.toolloaders;

import java.util.Map;

import com.anandhuarjunan.developertools.core.database.persistables.Tools;
import com.anandhuarjunan.developertools.core.exceptions.ToolError;

public interface ToolLoader {

	
	
	public void loadTool(Tools tool,Map<String,Object> toolArgs) throws ToolError;
	public void loadTool(Tools tool) throws ToolError;
}
