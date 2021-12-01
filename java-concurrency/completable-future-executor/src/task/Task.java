package task;

import java.util.concurrent.*;

public class Task {

    ExecutorService service = Executors.newSingleThreadExecutor();

    ExecutorService service2 = Executors.newSingleThreadExecutor();

    public String get() {
        CompletableFuture<String> futureA = CompletableFuture.supplyAsync(() -> Thread.currentThread().getName(), service);

        // change which service we run the supplier on
        CompletableFuture<String> futureB = CompletableFuture.supplyAsync(() -> Thread.currentThread().getName(), service);

        // we want each future to run on a different executor so the thread names are different
        CompletableFuture<Boolean> sameString = futureA.thenCombine(futureB, String::equals);

        try {
            // waits for the future to be completed for up to 1000 millis, then it will throw a TimeoutException
            return String.valueOf(sameString.get(1000, TimeUnit.MILLISECONDS));
        } catch (Exception e) {
            return "future.get threw exception " + e.getClass().getSimpleName();
        }
    }

}