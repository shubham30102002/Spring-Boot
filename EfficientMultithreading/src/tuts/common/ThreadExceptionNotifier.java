package tuts.common;

import java.lang.Thread.UncaughtExceptionHandler;

public class ThreadExceptionNotifier implements UncaughtExceptionHandler {

	@Override
	public void uncaughtException(Thread t, Throwable e) {
		sendMail(t.getName(), e);
	}
	
	private void sendMail(String threadName, Throwable e) {
		//Send email here
		
		System.out.println("=> " + this + " notified developer with en email => Thread - \"" + threadName + "\" threw " +
				"exception: " + e);
	}

}
