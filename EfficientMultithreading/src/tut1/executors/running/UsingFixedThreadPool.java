package tut1.executors.running;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import tuts.common.LoopTaskA;

public class UsingFixedThreadPool {

	public static void main(String[] args) {
		System.out.println("Main thread starts here...");
		
		ExecutorService execService = Executors.newFixedThreadPool(6);
		
		for (int i = 0; i < 6; i++) {
			execService.execute(new LoopTaskA());
		}
		
		execService.shutdown();
		
		execService.execute(new LoopTaskA());
		
		System.out.println("Main thread ends here...");
	}
}