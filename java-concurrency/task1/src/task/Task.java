package task;

import java.util.ArrayList;

public class Task {

    public String get() {
        ArrayList<String> strings = new ArrayList<String>(2);
        Thread thread = new Thread(() -> {
            sleep();
            strings.add("called from thread");
        });
//        start(thread);
        strings.add("called from main");
        join(thread);
        return String.join(",", strings);
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