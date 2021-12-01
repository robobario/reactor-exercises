package task;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Task {
    public static class Counter {
        int counter = 0;
        int errorCounter = 0;

        Lock lock = new ReentrantLock();
        Lock lock2 = new ReentrantLock();

        void inc() {
            try {
                System.out.println("attempting to lock lock1");
                lock.lock();
                System.out.println("lock1 locked");
                sleep(500);
                System.out.println("attempting to lock lock2");
                lock2.lock();
                System.out.println("lock2 locked");
                try {
                    // not very dangerous but if this was work that could throw we want to unlock
                    counter++;
                } finally {
                    lock2.unlock();
                    lock.unlock();
                }
            } catch (Exception e){
                errorCounter ++;
            }
        }

        public void incReverseLocks() {
            System.out.println("attempting to lock lock2");
            lock2.lock();
            System.out.println("lock2 locked");
            sleep(500);
            System.out.println("attempting to lock lock1");
            lock.lock();
            System.out.println("lock1 locked");
            try {
                // not very dangerous but if this was work that could throw we want to unlock
                counter++;
            } finally {
                lock2.unlock();
                lock.unlock();
            }
        }
    }

    private static void sleep(long l) {
        try {
            Thread.sleep(l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int run() {
        Counter counter = new Counter();
        Thread thread1 = runAsync(counter::inc);
        Thread thread2 = runAsync(counter::incReverseLocks);
        join(thread1);
        join(thread2);
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
            thread.join(5000);
            if(thread.isAlive()){
                throw new RuntimeException("thread " + thread.getName() + " still running after 5 seconds, probably deadlocked");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException("thread never completed");
        }
    }

}