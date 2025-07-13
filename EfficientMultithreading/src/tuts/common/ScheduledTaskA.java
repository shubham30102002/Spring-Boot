package tuts.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import tuts.utils.TimeUtils;

public class ScheduledTaskA extends TimerTask {

	private long sleepTime;
	
	private static int count = 0;
	private int instanceNumber;
	private String taskId;
	
	private SimpleDateFormat dateFormatter = new SimpleDateFormat("HH:mm:ss.SSS");
	
	public ScheduledTaskA(long sleepTime) {
		this.sleepTime = sleepTime;
		
		this.instanceNumber = ++count;
		this.taskId = "ScheduledTaskA-" + instanceNumber;
	}
	
	@Override
	public void run() {
		Date startTime = new Date();
		Date scheduledTime = new Date(super.scheduledExecutionTime());
		
		String currentThreadName = Thread.currentThread().getName();
		
		System.out.println("##### [" + currentThreadName + "] <" + taskId + 
				"> SCHEDULED TO RUN AT : " + dateFormatter.format(scheduledTime) + 
				", ACTUALLY STARTED AT : " + dateFormatter.format(startTime) + " #####");
		
		try {
			TimeUnit.MILLISECONDS.sleep(sleepTime);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Date endTime = new Date();
		
		System.out.println("***** [" + currentThreadName + "] <" + taskId + 
				"> COMPLETED AT : " + dateFormatter.format(endTime) + " *****" +
				"\n\tDELAYED START BY : " + TimeUtils.getTimeDifferenceInSeconds(scheduledTime,  startTime) +
				"\n\tRUN DURATION     : " + TimeUtils.getTimeDifferenceInSeconds(startTime,  endTime) + "\n");
	}
}
