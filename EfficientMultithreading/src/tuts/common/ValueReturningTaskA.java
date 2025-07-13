package tuts.common;

import java.util.concurrent.TimeUnit;

public class ValueReturningTaskA implements Runnable {

	private int a;
	private int b;
	private long sleepTime;
	private int sum;
	
	private static int count = 0;
	private int instanceNumber;
	private String taskId;
	
	private volatile boolean done = false;
	
	public ValueReturningTaskA(int a, int b, long sleepTime) {
		this.a = a;
		this.b = b;
		this.sleepTime = sleepTime;
		
		this.instanceNumber = ++count;
		this.taskId = "ValueReturningTaskA-" + instanceNumber;
	}
	
	@Override
	public void run() {
		String currentThreadName = Thread.currentThread().getName();
		
		System.out.println("##### [" + currentThreadName + "] <" + taskId + 
				"> STARTING #####");
		
		try {
			TimeUnit.MILLISECONDS.sleep(sleepTime);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sum = a + b;
		
		System.out.println("***** [" + currentThreadName + "] <" + taskId + 
				"> CALCULATION COMPLETED *****");
		
		done = true;
		
		synchronized(this) {
			System.out.println("[" + currentThreadName + "] <" + taskId + 
					"> NOTIFYING ...");
			this.notify();
		}
	}
	
	public int getSum() {
		synchronized(this) {
			if (!done) {
				try {
					System.out.println("[" + Thread.currentThread().getName() + 
							"] WAITING for result from " + taskId + "...");
					this.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return sum;
	}

}
