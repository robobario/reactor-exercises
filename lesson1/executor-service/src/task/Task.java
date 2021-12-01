package task;

import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Task {

    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    public String get() {
        ArrayList<String> strings = new ArrayList<String>(2);
        Runnable runnable = () -> {
            sleep();
            strings.add("called from thread");
        };
        strings.add("called from main");
        shutdownAndWaitForRunnables();
        return String.join(",", strings);
    }

    private void shutdownAndWaitForRunnables() {
        executor.shutdown();
        try {
            executor.awaitTermination(10000, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void sleep() {
        try {
            /*
            Causes the currently executing thread to sleep (temporarily cease execution)
            for the specified number of milliseconds,
            subject to the precision and accuracy of system timers and schedulers.
            */
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}