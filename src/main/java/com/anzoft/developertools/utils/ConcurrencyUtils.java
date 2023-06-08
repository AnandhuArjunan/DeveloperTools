/*
 * Copyright 2021 : Anandhu Arjunan
 * Made with Love In India.
 * Email : anandhuka97@gmail.com
╚🍧  🎀  𝐵𝓎  𝒜𝓃𝒶𝓃𝒹𝒽𝓊 𝒜𝓇𝒿𝓊𝓃𝒶𝓃  🎀  🍧 
 */
package com.anzoft.developertools.utils;

import java.util.concurrent.ThreadFactory;

import com.anzoft.developertools.constants.CommonConstant;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

import javafx.concurrent.Task;

public class ConcurrencyUtils {

public static void fxTaskExecuter(Task<?> task)  {
	Thread thread = new Thread(task);
	thread.start();
}
	

public static ThreadFactory giveNameToTheThread(String name) {
    return new ThreadFactoryBuilder().setNameFormat(CommonConstant.THREAD_IDENTIFIER+name+"-%d").build();
}

	
}
