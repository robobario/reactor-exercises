package task;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class Task {


    public String get() {
        CompletableFuture<String> future = new CompletableFuture<>();
        new Thread(() -> {
            // do nothing
            String result = "value from runnable";
        }).start();
        try {
            // waits for the future to be completed for up to 1000 millis, then it will throw a TimeoutException
            return future.get(1000, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            return "future.get threw exception " + e.getClass().getSimpleName();
        }
    }

}