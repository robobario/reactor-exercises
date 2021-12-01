package task;

import java.util.concurrent.atomic.AtomicReference;

public class Task {

    public String run() {
        AtomicReference<String> atomicReference = new AtomicReference<>();
        Thread thread = runAsync(() -> {
            try {
                Thread.sleep(3000);
                atomicReference.set("finished");
            } catch (InterruptedException e) {
                e.printStackTrace();
                atomicReference.set("interrupted");
            }
        });
        join(thread);
        return atomicReference.get();
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