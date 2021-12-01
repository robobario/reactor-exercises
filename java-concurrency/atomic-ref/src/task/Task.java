package task;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class Task {
    public static class Counter {
        AtomicReference<Integer> counter = new AtomicReference<>(0);

        void inc() {
            counter.getAndUpdate(integer -> integer + 1);
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
        return counter.counter.get();
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