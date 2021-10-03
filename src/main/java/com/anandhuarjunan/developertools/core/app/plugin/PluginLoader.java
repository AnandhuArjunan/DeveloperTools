/*
 * 
 */
package com.anandhuarjunan.developertools.core.app.plugin;

import java.io.File;
import java.util.Properties;

import com.anandhuarjunan.developertools.core.exceptions.InternalError;

public interface PluginLoader {

    
    public Properties load(File plugin) throws InternalError;
    public boolean validate(File pluginName);
    public Properties init(File pluginName) throws InternalError;

    
}
