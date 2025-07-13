package tut1.executors.running;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import tuts.common.LoopTaskA;

public class UsingCachedThreadPool {

	public static void main(String[] args) {
		System.out.println("Main thread starts here...");
		
		ExecutorService execService = Executors.newCachedThreadPool();
		
		for (int i = 0; i < 6; i++) {
			execService.execute(new LoopTaskA());
		}
		
		execService.shutdown();
		
		System.out.println("Main thread ends here...");
	}
}