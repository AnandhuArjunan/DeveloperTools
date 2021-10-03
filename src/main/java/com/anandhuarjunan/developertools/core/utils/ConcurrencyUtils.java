
package com.anandhuarjunan.developertools.core.utils;

import java.util.concurrent.ThreadFactory;

import com.anandhuarjunan.developertools.core.constants.CommonConstant;
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
