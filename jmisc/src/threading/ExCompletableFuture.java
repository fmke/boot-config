package threading;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class ExCompletableFuture {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
 		CompletableFuture<Integer> c = CompletableFuture.supplyAsync( () -> 2)
 								.thenApply(x -> x * 2)
 								.thenApply(x -> x * 3)
 								.thenApply(x -> x * 3);
 		
 		System.out.println(c.get());
	}
}
