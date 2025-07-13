package tuts.common;

public class NamedExceptionHandlingAlternatingThreadFactory extends NamedThreadsFactory {

	private int count = 0;
	
	@Override
	public Thread newThread(Runnable r) {
		Thread t = super.newThread(r);
		
		if (++count % 2 == 0) {
			t.setUncaughtExceptionHandler(new ThreadExceptionNotifier());
		}
		
		return t;
	}
}
