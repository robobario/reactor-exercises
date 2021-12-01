package task;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class Task {


    public String get() {
        CompletableFuture<Long> futureA = new CompletableFuture<>();
        CompletableFuture<Long> futureB = new CompletableFuture<>();
        new Thread(() -> {
            futureA.complete(5L);
        }).start();
        new Thread(() -> {
            futureB.complete(6L);
        }).start();

        // change here
        CompletableFuture<Long> sumFuture = futureA;

        try {
            // waits for the future to be completed for up to 1000 millis, then it will throw a TimeoutException
            return String.valueOf(sumFuture.get(1000, TimeUnit.MILLISECONDS));
        } catch (Exception e) {
            return "future.get threw exception " + e.getClass().getSimpleName();
        }
    }

}