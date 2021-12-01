package task;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class Task {

    public String run() {
        AtomicReference<String> s = new AtomicReference<>("initial value");
        CountDownLatch latch = new CountDownLatch(1);
        runAsync(() -> {
            sleep();
            s.set("asynchronously initialized value");
            //countdown the latch
        });
        try {
            boolean success = latch.await(3000, TimeUnit.MILLISECONDS);
            if(success) {
                return s.get();
            } else {
                return "latch await failed";
            }
        } catch (InterruptedException e) {
            return "interrupted while waiting for latch";
        }
    }

    private void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private Thread runAsync(Runnable runnable) {
        Thread thread = new Thread(runnable);
        start(thread);
        return thread;
    }


    private void start(Thread thread) {
        /*
          Causes this thread to begin execution; the Java Virtual Machine calls the run method of this thread.
          The result is that two threads are running concurrently:
          the current thread (which returns from the call to the start method)
          and the other thread (which executes its run method).
         */
        thread.start();
    }

    private void join(Thread thread) {
        try {
            // Waits for this thread to die.
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}