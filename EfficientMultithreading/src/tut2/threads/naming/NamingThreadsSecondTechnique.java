package tut2.threads.naming;

import java.util.concurrent.TimeUnit;

import tuts.common.LoopTaskC;

public class NamingThreadsSecondTechnique {

	public static void main(String[] args) {
		String currentThreadName = Thread.currentThread().getName();
		
		System.out.println("[" + currentThreadName + "] Main thread starts here...");
		
		new Thread(new LoopTaskC(), "Worker-1").start();
		
		Thread t2 = new Thread(new LoopTaskC());
//		t2.setName("Worker-2");
		t2.start();
		
		try {
			TimeUnit.MILLISECONDS.sleep(800);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("[" + currentThreadName + "] Renaming second thread now...");
		t2.setName("Worker-2");
		
		System.out.println("[" + currentThreadName + "] Main thread ends here...");
	}
}
