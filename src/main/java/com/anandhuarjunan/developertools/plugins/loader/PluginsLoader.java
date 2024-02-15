package com.anandhuarjunan.developertools.plugins.loader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.util.Enumeration;
import java.util.Objects;
import java.util.Scanner;


import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import com.anandhuarjunan.developertools.annotations.Tool;
import com.anandhuarjunan.developertools.database.entity.Plugins;
import com.anandhuarjunan.developertools.database.entity.Tools;
import com.anandhuarjunan.developertools.database.services.common.PersistEntity;
import com.anandhuarjunan.developertools.exceptions.DTRuntimeException;
import com.anandhuarjunan.developertools.exceptions.ServiceException;
import com.anandhuarjunan.developertools.utils.HibernateUtils;
import com.anandhuarjunan.developertools.utils.JarUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;


public class PluginsLoader {
	static Logger log = Logger.getLogger(PluginsLoader.class);

	private static final String PLUGINS_PATH = "plugins/";
	private static final String PLUGIN_META = "plugin.json";

	public void scanAndLoad() {
		File libPath = new File(PLUGINS_PATH);
		File[] plugins = libPath.listFiles((dir, name) -> name.endsWith(".jar"));
		for (File plugin : plugins) {
			try {
				ClassLoader classLoader = JarUtils.loadJar(plugin);
				String pluginMetaLoc = fileExists(plugin);
				if (pluginMetaLoc != null) {
					ObjectMapper objectMapper = new ObjectMapper();
					Plugin pluginMeta = objectMapper.readValue(readFileContent(classLoader, pluginMetaLoc),
							Plugin.class);
					Objects.requireNonNull(pluginMeta.getArtifactId(), plugin.getName() + "Artifact ID in plugin.json cannot be null");
					Objects.requireNonNull(pluginMeta.getGroupId(), plugin.getName() + "Group ID in plugin.json cannot be null");
					Objects.requireNonNull(pluginMeta.getName(), plugin.getName() + "Name in plugin.json cannot be null");
					Objects.requireNonNull(pluginMeta.getVersion(), plugin.getName() + "Version in plugin.json cannot be null");
					Objects.requireNonNull(pluginMeta.getDescription(), plugin.getName() + "Description in plugin.json cannot be null");
					Objects.requireNonNull(pluginMeta.getDevToolsVersion(), plugin.getName() + "DevToolsVersion in plugin.json cannot be null");
					loadPlugin(plugin, pluginMeta);
				} else {
					throw new DTRuntimeException(plugin.getName() + " does not have plugin.json");
				}
			} catch (NoSuchMethodException | MalformedURLException | IllegalAccessException
					| InvocationTargetException e) {
				throw new DTRuntimeException("Failed to load plugin " + plugin.getName());
			} catch (JsonProcessingException e) {
				throw new DTRuntimeException(
						"Failed to load plugin " + plugin.getName() + "due an error in plugin.json");

			}
		}
	}

	private void loadPlugin(File file ,Plugin pluginMeta) {
	        String packageToScan = pluginMeta.getGroupId() + "." + pluginMeta.getArtifactId(); // Replace with your package name
	        Reflections reflections = new Reflections(new ConfigurationBuilder()
	        	    .setScanners(new SubTypesScanner(false), new TypeAnnotationsScanner()) // Configure the SubTypesScanner
	        	    .addUrls(ClasspathHelper.forPackage(packageToScan))); 	   
	        Set<Class<?>> annotatedClasses = reflections.getTypesAnnotatedWith(Tool.class);
	        for (Class<?> annotatedClass : annotatedClasses) {
	        	PersistEntity<Tool> persistEntity = new PersistEntity<>();
	        	try {
	        		Plugins plugins = new Plugins();
					plugins.setGroupId(pluginMeta.getGroupId());
					plugins.setArtifactId(pluginMeta.getArtifactId());
					plugins.setName(pluginMeta.getName());
					plugins.setVersion(pluginMeta.getVersion());
					plugins.setDescription(pluginMeta.getDescription());
					plugins.setDevToolsVersion(pluginMeta.getDevToolsVersion());
					plugins.setFileName(file.getName());
					Tools tools = new Tools();
					for(Annotation annotation: annotatedClass.getAnnotations()) {
						if(annotation instanceof Tool tool) {
							tools.setToolName(tool.name());
							tools.setToolDescription(tool.description());
							tools.setToolPlgin(plugins);
							tools.setToolImplementation(annotatedClass.getName());
							tools.setToolCategory(tool.category()!=null?String.join(", ", tool.category()):null);
							truncate("TOOLS");
							truncate("PLUGINS");

							persistEntity.persist(tools);
							
							
						}
					}
				
				} catch (ServiceException e) {
					throw new DTRuntimeException("Failed to load plugin " + pluginMeta.getArtifactId() +""+e.getMessage());
				}
	        }
	}

	private static String readFileContent(ClassLoader classLoader, String filePath) {
		try (InputStream inputStream = classLoader.getResourceAsStream(filePath);
				Scanner scanner = new Scanner(inputStream)) {
			if (inputStream != null) {
				return scanner.useDelimiter("\\A").hasNext() ? scanner.next() : "";
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}

	private static String fileExists(File plugin) {
		 try (JarFile jarFile = new JarFile(plugin)) {
	            Enumeration<JarEntry> entries = jarFile.entries();

	            while (entries.hasMoreElements()) {
	                JarEntry entry = entries.nextElement();
	                String entryName = entry.getName();
	                // Check if the entry is the file you are searching for
	                if (entryName.contains(PLUGIN_META)) {
	                    System.out.println("File found in JAR: " + entryName);
	                    return entryName;
	                    // You can do further processing, or break out of the loop if you only need the first occurrence
	                    // Example: InputStream inputStream = jarFile.getInputStream(entry);
	                }
	            }

	            // If you reach here and haven't found the file, it doesn't exist in the JAR
	            System.out.println("File not found in JAR: " + PLUGIN_META);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		return null;
		
		
	}
	private void truncate(String tableName) {
		Session session = null;
        Transaction transaction = null;

        try {
            session = HibernateUtils.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            // Replace "your_table_name" with the actual name of your table

            // Create a native SQL query to truncate the table
            String truncateQuery = "DELETE FROM " + tableName;

            // Execute the query
            session.createSQLQuery(truncateQuery).executeUpdate();

            transaction.commit();
            System.out.println("Table truncated successfully.");
	}catch(Exception e) {
		e.printStackTrace();
	}
		
	}
}
