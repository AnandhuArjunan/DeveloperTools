/*
 * 
 */
package com.anandhuarjunan.developertools.core.app.plugin;

import java.io.File;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.zeroturnaround.zip.ZipUtil;

import com.anandhuarjunan.developertools.core.exceptions.InternalError;

public  abstract class AbstractPluginLoader implements PluginLoader {
    

    @Override
    public boolean validate(File plugin) {
	if(null != plugin) {
	       return ZipUtil.containsEntry(plugin, "DTPLUGIN.properties") ;
	       
	}
	return false;
    }


@Override
    public Properties init(File pluginfile) throws InternalError {
    	if(validate(pluginfile)) {
    	return load(pluginfile);
    	}
	return null;
    }

}
