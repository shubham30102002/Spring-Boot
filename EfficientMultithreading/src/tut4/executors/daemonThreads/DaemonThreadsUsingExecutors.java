package tut4.executors.daemonThreads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import tuts.common.LoopTaskD;
import tuts.common.NamedDaemonThreadsFactory;

public class DaemonThreadsUsingExecutors {

	public static void main(String[] args) {
		String currentThreadName = Thread.currentThread().getName();
		System.out.println("[" + currentThreadName + "] Main thread starts here...");
		
		ExecutorService execService = Executors.newCachedThreadPool(
				new NamedDaemonThreadsFactory());
		
		execService.execute(new LoopTaskD(100));
		execService.execute(new LoopTaskD(200));
		execService.execute(new LoopTaskD(300));
		execService.execute(new LoopTaskD(400));
		
		execService.shutdown();
		
		System.out.println("[" + currentThreadName + "] Main thread ends here...");
	}
}
