/*
 * Copyright 2021 : Anandhu Arjunan
 * Made with Love In India.
 * Email : anandhuka97@gmail.com
╚🍧  🎀  𝐵𝓎  𝒜𝓃𝒶𝓃𝒹𝒽𝓊 𝒜𝓇𝒿𝓊𝓃𝒶𝓃  🎀  🍧 
 */
package com.anzoft.developertools.controller.toolloaders;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;

import com.anzoft.developertools.app.headerpanel.message.NotifyMessage;
import com.anzoft.developertools.constants.ExternalToolTypes;
import com.anzoft.developertools.database.persistables.ExternalTools;
import com.anzoft.developertools.database.persistables.Tools;
import com.anzoft.developertools.database.services.common.FindEntityByFieldsService;
import com.anzoft.developertools.exceptions.InternalError;
import com.anzoft.developertools.exceptions.ServiceException;
import com.anzoft.developertools.exceptions.ToolError;
import com.anzoft.developertools.utils.ExecutableUtils;

import javafx.scene.control.TabPane;

public class ExternalToolLoader extends AbstractToolLoader {

	public ExternalToolLoader(TabPane tab,ExecutorService executorService) {
		super(tab,executorService);
	}

	@Override
	public void load(Tools tool) throws ToolError {
		FindEntityByFieldsService<ExternalTools> dbService = new FindEntityByFieldsService<>();
		ExternalTools externalTools = null;
		try {
			Map<String, Object> map = new HashMap<>();
			map.put("tools", tool);
			externalTools = dbService.findEntityByFields(ExternalTools.class, map).getResult();
			if (null != externalTools) {
				openExternalTool(externalTools);
			}
		} catch (ServiceException e) {
			NotifyMessage.notifyError("Unable to Load External Tool Configuration");
		} catch (IOException e) {
		    NotifyMessage.notifyError("The File is Not Accessable");
		} catch (InternalError e) {
		    NotifyMessage.notifyError("An Error occurred - "+ e.getMessage());

		}
		

	}

	public void openExternalTool(ExternalTools externalTool) throws IOException, InternalError {
		switch (externalTool.getAppType().getType()) {
		case ExternalToolTypes.INVOKE_JAR:

			ExecutableUtils.invokeRunnableJar(externalTool.getWorkingDir(), externalTool.getFileName(),
					externalTool.getToolCommand());

			break;
		case ExternalToolTypes.INVOKE_WINDOWS_BAT:

			// ExecutableUtils.invokeWindowsBat(externalTool.getCommand());

			break;
		case ExternalToolTypes.INVOKE_WINDOWS_EXE:
			// ExecutableUtils.invokeWindowsExe(externalTool.getCommand());

			break;
		default:
			throw new IllegalArgumentException("External Tool Type is wrong");
		}
	}
	
}

