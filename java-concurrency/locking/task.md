# Threads - Locks

Another tool available that is similar but different to synchronization are the Lock implementations provided by Java.

## ReentrantLock
The re-entrant lock is similar to using synchronized(this) locking, which implicitly uses a lock under the hood. But a ReentrantLock has a `lockInterruptibly` method, so a thread interrupt can be handled. It also has `tryLock` methods with optional timeout so you can control how long it awaits a lock, you can't do that with `synchronized`.

Typical usage:
```java
class X {
   private final ReentrantLock lock = new ReentrantLock();

   public void m() {
     lock.lock();  // block until condition holds
     try {
       // ... method body
     } finally {
       lock.unlock()
     }
   }
 }
```

## Task

Change the locking code so that it blocks until the lock is available. Currently it uses tryLock to try and lock immediately without blocking, so when another thread has the lock it doesn't increment.

You can implement this by using `lock`. Or you could call tryLock repeatedly (with a timeout to not thrash your poor friend CPU).