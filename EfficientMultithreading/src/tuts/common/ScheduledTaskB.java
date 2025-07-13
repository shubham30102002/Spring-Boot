package tuts.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import tuts.utils.TimeUtils;

public class ScheduledTaskB implements Runnable {

	private long sleepTime;
	
	private static int count = 0;
	private int instanceNumber;
	private String taskId;
	
	private SimpleDateFormat dateFormatter = new SimpleDateFormat("HH:mm:ss.SSS");
	
	public ScheduledTaskB(long sleepTime) {
		this.sleepTime = sleepTime;
		
		this.instanceNumber = ++count;
		this.taskId = "ScheduledTaskB-" + instanceNumber;
	}
	
	@Override
	public void run() {
		Date startTime = new Date();
		
		String currentThreadName = Thread.currentThread().getName();
		
		System.out.println("##### [" + currentThreadName + "] <" + taskId + 
				"> STARTED AT : " + dateFormatter.format(startTime) + " #####");
		
		try {
			TimeUnit.MILLISECONDS.sleep(sleepTime);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Date endTime = new Date();
		
		System.out.println("***** [" + currentThreadName + "] <" + taskId + 
				"> COMPLETED AT : " + dateFormatter.format(endTime) + " *****" +
				"\n\tRUN DURATION     : " + TimeUtils.getTimeDifferenceInSeconds(startTime,  endTime) + "\n");
	}
}
