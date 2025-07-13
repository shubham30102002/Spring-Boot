package tuts.common;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.Arrays;
import java.util.List;

public class NamedExceptionHandlingThreadFactory extends NamedThreadsFactory {

	List<UncaughtExceptionHandler> handlers = Arrays.asList(new ThreadExceptionHandler(), new ThreadExceptionNotifier());
	
	@Override
	public Thread newThread(Runnable r) {
		Thread t = super.newThread(r);
		t.setUncaughtExceptionHandler(handlers.get((int)System.currentTimeMillis() % 2));
		
		return t;
	}
}
