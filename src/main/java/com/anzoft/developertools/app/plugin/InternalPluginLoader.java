package com.anzoft.developertools.app.plugin;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

import org.zeroturnaround.zip.ZipUtil;

import com.anzoft.developertools.database.persistables.Category;
import com.anzoft.developertools.database.persistables.FxmlLoaderTools;
import com.anzoft.developertools.database.persistables.InternalTools;
import com.anzoft.developertools.database.persistables.ToolTypes;
import com.anzoft.developertools.database.persistables.Tools;
import com.anzoft.developertools.database.services.common.FindEntityService;
import com.anzoft.developertools.database.services.common.PersistEntity;
import com.anzoft.developertools.exceptions.InternalError;
import com.anzoft.developertools.exceptions.ServiceException;
import com.anzoft.developertools.utils.PluginLoaderUtils;
import com.anzoft.developertools.utils.PropertyUtils;

public class InternalPluginLoader extends AbstractPluginLoader {

    @Override
    public Properties load(File pluginFile) throws InternalError {
	byte[] bytes = ZipUtil.unpackEntry(pluginFile, "DTPLUGIN.properties");
    	String content = new String(bytes,StandardCharsets.UTF_8);
    	try {
	    Properties properties  = PropertyUtils.parsePropertiesString(content);
	    importPlugin(properties);
	    PluginLoaderUtils.loadJar(pluginFile);
	    return properties;
	} catch (IOException | ServiceException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
	    throw new InternalError("Error During Plugin Load - "+pluginFile.getName()+" - ", e);
	}
    }

   
    public void importPlugin(  Properties properties ) throws ServiceException {
	
	String toolId = properties.getProperty("NAME").replace(" ", "_");
	Tools tools = new Tools();
	tools.setDescription(properties.getProperty("DESCRIPTION"));
	tools.setClosable("Y".equalsIgnoreCase(properties.getProperty("CLOSABLE")));
	tools.setMultiTabSupport("Y".equalsIgnoreCase(properties.getProperty("MULTI_TAB")));
	tools.setVisible("Y".equalsIgnoreCase(properties.getProperty("SHOW_IN_MENU")));
	
	FindEntityService<Category> findCategory = new FindEntityService<>();

        tools.setCategory(findCategory.findEntity(Category.class, properties.getProperty("CATEGORY")).getResult());

	FindEntityService<ToolTypes> dbServiceq = new FindEntityService<>();

        tools.setToolType(dbServiceq.findEntity(ToolTypes.class, "F").getResult());
	tools.setId(toolId);
	tools.setToolName(properties.getProperty("NAME"));
	
	FxmlLoaderTools fxmlLoaderTools = new FxmlLoaderTools();
	fxmlLoaderTools.setTools(tools);
	
	fxmlLoaderTools.setFxmlPath(properties.getProperty("FXML_RELATIVE_LOC"));
	fxmlLoaderTools.setStylesheets(properties.getProperty("CSS_RELATIVE_LOC"));
	
	InternalTools internalTools = new InternalTools();
	internalTools.setTool(tools);
	internalTools.setClassName(properties.getProperty("CLASS"));
	
	
	
	PersistEntity<Tools> dbService = new PersistEntity<>();
	dbService.persist(tools);
	PersistEntity<FxmlLoaderTools> dbServiceex = new PersistEntity<>();
	dbServiceex.persist(fxmlLoaderTools);
    }
}
