package task;

public class Task {

    public long serial() {
        long start = System.currentTimeMillis();
        sleep(1000);
        sleep(1000);
        return System.currentTimeMillis() - start;
    }

    public long concurrent() {
        long start = System.currentTimeMillis();
        Thread thread = runAsync(() -> {
            sleep(1500);
            sleep(1500);
        });
        join(thread);
        return System.currentTimeMillis() - start;
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

    private void sleep(int milliseconds) {
        try {
            /*
            Causes the currently executing thread to sleep (temporarily cease execution)
            for the specified number of milliseconds,
            subject to the precision and accuracy of system timers and schedulers.
            */
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}