package tut7.executors.exceptionHandling;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import tuts.common.ExceptionLeakingTask;
import tuts.common.NamedExceptionHandlingAlternatingThreadFactory;
import tuts.common.ThreadExceptionHandler;

public class HandlingExecutorsUncaughtExceptions_DefaultsNOverrides {

	public static void main(String[] args) {
		String currentThreadName = Thread.currentThread().getName();
		System.out.println("[" + currentThreadName + "] Main thread starts here...");
		
		Thread.setDefaultUncaughtExceptionHandler(new ThreadExceptionHandler("THE_DEFAULT_ONE"));
		
		ExecutorService execService = Executors.newCachedThreadPool(new NamedExceptionHandlingAlternatingThreadFactory());
		
		execService.execute(new ExceptionLeakingTask());
		execService.execute(new ExceptionLeakingTask());
		execService.execute(new ExceptionLeakingTask());
		execService.execute(new ExceptionLeakingTask());
		
		execService.shutdown();
		
		System.out.println("[" + currentThreadName + "] Main thread ends here...");
	}
}
