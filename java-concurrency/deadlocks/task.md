# Threads - Locks - Deadlocking

One thing to be super careful with is deadlocking. If you are playing with multiple locks you can deadlock things by trying to lock in this order:

```
Thread1: lock1.lock()
Thread2: lock2.lock()
Thread1: lock2.lock() 
Thread2: lock1.lock() <<< dead lock
```

Both threads are waiting on a lock to unlock :(

## Task

Fix the deadlock by making the two increment methods lock the locks in the same order. This will allow one thread to acquire both locks and proceed.

## Note

This is very similar to some recent issues we have had in postgres, concurrent queries trying to update rows of the table deadlocking. It acquires row level locks on update so if your clients do:

```
CLIENT 1: update table set x=y order by id asc;
CLIENT 2: update table set x=y order by id desc;
```

Then it can make it acquire conflicting row locks (because the ordering impacts the order that it acquires row locks) and deadlock the updates.

Fixed a similar way by making sure all our parallel writes update rows in the same order.