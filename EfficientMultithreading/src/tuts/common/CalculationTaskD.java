package tuts.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import tuts.utils.TimeUtils;

public class CalculationTaskD implements Callable<Integer> {

	private int a;
	private int b;
	private long sleepTime;
	
	private static int count = 0;
	private int instanceNumber;
	private String taskId;
	
	private SimpleDateFormat dateFormatter = new SimpleDateFormat("HH:mm:ss.SSS");
	
	public CalculationTaskD(int a, int b, long sleepTime) {
		this.a = a;
		this.b = b;
		this.sleepTime = sleepTime;
		
		this.instanceNumber = ++count;
		this.taskId = "CalculationTaskD-" + instanceNumber;
	}
	
	@Override
	public Integer call() throws Exception {
		Date startTime = new Date();
		String currentThreadName = Thread.currentThread().getName();
		
		System.out.println("##### [" + currentThreadName + "] <" + taskId + "> STARTED at " + 
				dateFormatter.format(startTime) + " #####");
		
		TimeUnit.MILLISECONDS.sleep(sleepTime);
		Date endTime = new Date();
		
		System.out.println("***** [" + currentThreadName + "] <" + taskId + "> CALCULATION COMPLETED at " + 
				dateFormatter.format(endTime) + " *****" +
				"\n\tRUN DURATION     : " + TimeUtils.getTimeDifferenceInSeconds(startTime, endTime) + "\n");
		
		return a + b;
	}
	
}
