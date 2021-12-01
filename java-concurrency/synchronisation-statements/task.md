# Threads - Synchronised Statements
Another way to synchronise is to use a synchronized statement like:

```java
public void addName(String name) {
    synchronized(this) {
        lastName = name;
    }
}
```
The java tuturial [explains it well](https://docs.oracle.com/javase/tutorial/essential/concurrency/locksync.html)

The above invocation is very similar to synchronizing the method. When you add the `synchronized` keyword to the method it automatically acquires an internal lock on `this`, similar to what `synchronized(this){}` is doing.

## Task

Use a `synchronized` statement in the `inc` method of Counter to avoid the race condition the same way as before.

## Why

This can be used for fine-grained synchronization, instead of locking on `this` you could synchronize on an Object and use it to independently lock different fields. So they could be updated concurrently, a write to A wouldn't block a write to B.

Here's the example from the java tutorial
```java
public class MsLunch {
    private long c1 = 0;
    private long c2 = 0;
    private Object lock1 = new Object();
    private Object lock2 = new Object();

    public void inc1() {
        synchronized(lock1) {
            c1++;
        }
    }

    public void inc2() {
        synchronized(lock2) {
            c2++;
        }
    }
}
```
with a caveat to be careful :O