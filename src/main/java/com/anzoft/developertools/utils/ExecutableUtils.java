/*
 * Copyright 2021 : Anandhu Arjunan
 * Made with Love In India.
 * Email : anandhuka97@gmail.com
╚🍧  🎀  𝐵𝓎  𝒜𝓃𝒶𝓃𝒹𝒽𝓊 𝒜𝓇𝒿𝓊𝓃𝒶𝓃  🎀  🍧 
 */
package com.anzoft.developertools.utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;

import com.anzoft.developertools.exceptions.InternalError;

public class ExecutableUtils {

	public static void invokeRunnableJar(String filePath,String fileName,String commandDB) throws  IOException, InternalError  {
		if(null != System.getProperty("java.home")) {
			String command  = StringUtils.isEmpty(commandDB)?(" java -jar "):commandDB;
			CommandLine cmdLine = CommandLine.parse(command+" "+ "\""+FilenameUtils.separatorsToSystem(filePath+File.separator+fileName)+"\"");
			DefaultExecutor executor = new DefaultExecutor();
			DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
			executor.execute(cmdLine, resultHandler);
		}
		else {
			throw new InternalError("Java is not Available");
		}
		
	}
	
	public static void invokeWindowsBat(String filePath) throws   IOException, InternalError {
		if(SystemUtils.IS_OS_WINDOWS) {
		CommandLine cmdLine = CommandLine.parse("cmd /c start \"\""+"\""+FilenameUtils.separatorsToSystem(filePath)+"\"");
		DefaultExecutor executor = new DefaultExecutor();
		DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
		executor.execute(cmdLine,resultHandler);
		}
		else {
			throw new InternalError("Windows Batch Files Cannot be Launched.");
		}
	}
	
	public static void invokeWindowsExe(String filePath) throws   IOException, InternalError {
		if(SystemUtils.IS_OS_WINDOWS) {
		Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+"\""+FilenameUtils.separatorsToSystem(filePath)+"\"");
		}
		else {
			throw new InternalError("Windows Batch Files Cannot be Launched.");
		}
	}
	
	
}
