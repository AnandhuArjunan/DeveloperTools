/*
 * 
 */
package com.anandhuarjunan.developertools.core.app.plugin;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

import org.zeroturnaround.zip.ZipUtil;

import com.anandhuarjunan.developertools.core.database.persistables.Category;
import com.anandhuarjunan.developertools.core.database.persistables.FxmlLoaderTools;
import com.anandhuarjunan.developertools.core.database.persistables.ToolTypes;
import com.anandhuarjunan.developertools.core.database.persistables.Tools;
import com.anandhuarjunan.developertools.core.database.services.common.FindEntityService;
import com.anandhuarjunan.developertools.core.database.services.common.PersistEntity;
import com.anandhuarjunan.developertools.core.exceptions.InternalError;
import com.anandhuarjunan.developertools.core.exceptions.ServiceException;
import com.anandhuarjunan.developertools.core.utils.PluginLoaderUtils;
import com.anandhuarjunan.developertools.core.utils.PropertyUtils;

public class FxmlPluginLoader extends AbstractPluginLoader{

    @Override
    public Properties load(File pluginFile) throws InternalError {
		return null;
	
	  
    }

   
    public void importPlugin(  Properties properties ) throws ServiceException {
	
    }
   
}
