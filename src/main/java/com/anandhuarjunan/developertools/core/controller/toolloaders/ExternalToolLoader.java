/*
 * 
 */
package com.anandhuarjunan.developertools.core.controller.toolloaders;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;

import com.anandhuarjunan.developertools.core.app.headerpanel.message.NotifyMessage;
import com.anandhuarjunan.developertools.core.constants.ExternalToolTypes;
import com.anandhuarjunan.developertools.core.database.persistables.ExternalTools;
import com.anandhuarjunan.developertools.core.database.persistables.Tools;
import com.anandhuarjunan.developertools.core.database.services.common.FindEntityByFieldsService;
import com.anandhuarjunan.developertools.core.exceptions.InternalError;
import com.anandhuarjunan.developertools.core.exceptions.ServiceException;
import com.anandhuarjunan.developertools.core.exceptions.ToolError;
import com.anandhuarjunan.developertools.core.utils.ExecutableUtils;

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

	@Override
	public void load(Tools tool, Map<String, Object> toolArgs) throws ToolError {
		load(tool);
	}


}

