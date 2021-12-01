package task;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class Task {
    public static class Counter {
        int counter = 0;

        Lock lock = new ReentrantLock();

        void inc() {
            boolean locked = lock.tryLock();
            try {
                // not very dangerous but if this was work that could throw we want to unlock
                counter++;
            } finally {
                if(locked) {
                    lock.unlock();
                }
            }
        }
    }

    public int run(int numThreads, int incrementsPerThread) {
        Counter counter = new Counter();
        List<Thread> threads = IntStream.range(0, numThreads).mapToObj(operand -> runAsync(() -> {
            for (int i = 0; i < incrementsPerThread; i++) {
                counter.inc();
            }
        })).collect(toList());
        threads.forEach(this::join);
        return counter.counter;
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