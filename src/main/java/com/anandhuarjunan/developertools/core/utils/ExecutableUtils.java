
package com.anandhuarjunan.developertools.core.utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;

import com.anandhuarjunan.developertools.core.exceptions.InternalError;

public class ExecutableUtils {

    private ExecutableUtils() {}

	public static void invokeRunnableJar(String filePath,String fileName,String commandDB) throws  IOException, InternalError  {
		if(null != System.getProperty("java.home")) {
			CommandLine cmdLine;
			if(StringUtils.isEmpty(commandDB)) {
			    cmdLine = new CommandLine("java");
			    cmdLine.addArgument("-jar");
			} else {
			    cmdLine = CommandLine.parse(commandDB);
			}
			cmdLine.addArgument(FilenameUtils.separatorsToSystem(filePath+File.separator+fileName), false);
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
		CommandLine cmdLine = new CommandLine("cmd");
		cmdLine.addArgument("/c");
		cmdLine.addArgument("start");
		cmdLine.addArgument("\"\"", false);
		cmdLine.addArgument(FilenameUtils.separatorsToSystem(filePath), false);
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
		CommandLine cmdLine = new CommandLine("rundll32");
		cmdLine.addArgument("url.dll,FileProtocolHandler");
		cmdLine.addArgument(FilenameUtils.separatorsToSystem(filePath), false);
		DefaultExecutor executor = new DefaultExecutor();
		DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
		executor.execute(cmdLine, resultHandler);
		}
		else {
			throw new InternalError("Windows Batch Files Cannot be Launched.");
		}
	}
	
	
}
