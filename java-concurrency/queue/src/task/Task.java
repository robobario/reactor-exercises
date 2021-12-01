package task;

import java.util.Queue;
import java.util.concurrent.*;

public class Task {

    ExecutorService service = Executors.newFixedThreadPool(2);
    
    private BlockingQueue<String> queue = new LinkedBlockingQueue<>();

    public String get() {
        service.execute(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            queue.offer("result pushed to queue");
        });

        // change future to call the blocking take method of the queue, so it will wait for an item
        CompletableFuture<String> pollFuture = CompletableFuture.supplyAsync(() -> {
            return "result from future";
        }, service);

        try {
            // waits for the future to be completed for up to 1000 millis, then it will throw a TimeoutException
            return String.valueOf(pollFuture.get(1000, TimeUnit.MILLISECONDS));
        } catch (Exception e) {
            return "future.get threw exception " + e.getClass().getSimpleName();
        }
    }

}