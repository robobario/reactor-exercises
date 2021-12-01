# Threads - Synchronisation

Using multiple threads introduces all kinds of danger and worry. For example if multiple threads are reading and incrementing an integer variable you may get this sequence of events.

1. int x = 1
2. Thread A: get x (sees value 1)
3. Thread B: get x (sees value 1)
4. Thread A: set x = (1)+1 (x == 2)
5. Thread B: set x = (1)+1 (x == 2)

This is a race-condition.

So the end result is `x == 2` where we had hoped to increment it twice.

The bluntest instrument we have to hit this race condition with is the `synchronized` keyword.

The [java concurrency tutorial](https://docs.oracle.com/javase/tutorial/essential/concurrency/syncmeth.html) has this to say about synchronized methods.

> First, it is not possible for two invocations of synchronized methods on the same object to interleave. When one thread is executing a synchronized method for an object, all other threads that invoke synchronized methods for the same object block (suspend execution) until the first thread is done with the object.
>
> Second, when a synchronized method exits, it automatically establishes a happens-before relationship with any subsequent invocation of a synchronized method for the same object. This guarantees that changes to the state of the object are visible to all threads.
## Task

Currently, we are making lots of concurrent increments to a variable in the Task class. This is vulnerable to the same race condition described above.

Synchronize the `inc` method of the `Counter` class to show that we can reliably increment the variable without this race condition.

## Note

It probably took a bit more time to execute the test after synchronising, this is the cost of synchronization. Only one thread can work while the rest are told to wait, effectively capping us to 1 core's worth of compute plus some overhead for scheduling.