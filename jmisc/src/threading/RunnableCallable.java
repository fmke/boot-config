package threading;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class RunnableCallable {

	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		Thread r = new Thread( () -> System.out.println("233") );
		r.start();
		
		ExecutorService ex = Executors.newFixedThreadPool(5);
		ex.submit( () -> System.out.println("233") );
		
		Future<Integer> fut = ex.submit(new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				Thread.sleep(2000);
				return 124;
			}
		});
		
		System.out.println("about to get");
		System.out.println(fut.get().toString()); // get will block
		System.out.println("5 seconds after get");
		
		
		Future<Integer> forever = ex.submit(new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				try {
					while(true) {
						Thread.sleep(500);
						//if( Thread.currentThread().isInterrupted() ) {
						//	System.out.println("interrrupted");
						//	return 777;
						//}
						
						System.out.println("running...");
					}
				}catch(InterruptedException ie) {
					System.out.println("interrrupted");
					return 777;
				}
			}
		});
		
		Thread.sleep(2000);
		ex.shutdown();		//stop acception tasks
		ex.shutdownNow();	//interrupt threads
		if(ex.awaitTermination(4,  TimeUnit.SECONDS)) {
			System.exit(1);  //last resort
		}
		
	}
}
