package tuts.common;

public class NamedDaemonThreadsFactory extends NamedThreadsFactory {

	private static int count = 0;
	
	public Thread newThread(Runnable r) {
		Thread t = super.newThread(r);
		
		if (++count % 2 == 0) {
			t.setDaemon(true);
		}
		
		return t;
	}
	
}
