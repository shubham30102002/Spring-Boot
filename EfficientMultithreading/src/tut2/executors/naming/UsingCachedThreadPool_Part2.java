package tut2.executors.naming;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import tuts.common.LoopTaskC;
import tuts.common.NamedThreadsFactory;

public class UsingCachedThreadPool_Part2 {

	public static void main(String[] args) throws InterruptedException {
		String currentThreadName = Thread.currentThread().getName();
		
		System.out.println("[" + currentThreadName + "] Main thread starts here...");
		
		ExecutorService execService = Executors.newCachedThreadPool(
				new NamedThreadsFactory());
		
		for (int i = 0; i < 3; i++) {
			execService.execute(new LoopTaskC());
		}
		
		TimeUnit.SECONDS.sleep(5);
		
		System.out.println("Submitting more tasks now...");
		
		for (int i = 0; i < 5; i++) {
			execService.execute(new LoopTaskC());
		}
		
		execService.shutdown();
		
		System.out.println("[" + currentThreadName + "] Main thread ends here...");
	}	
}
