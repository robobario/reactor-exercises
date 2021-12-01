# Semaphore

Another high level concurrency class available for coordination is the Semaphore.

> A counting semaphore. Conceptually, a semaphore maintains a set of permits. Each acquire blocks if necessary until a permit is available, and then takes it. Each release adds a permit, potentially releasing a blocking acquirer. However, no actual permit objects are used; the Semaphore just keeps a count of the number available and acts accordingly.
>
>Semaphores are often used to restrict the number of threads than can access some (physical or logical) resource

So we can again reimplement a solution to the synchronization race condition using a semaphore.

## Task

Update `Task` so that the semaphore only has one permit, therefore only one thread should be allowed to update the variable.

## Notes

So a usage for this could be; if you have 5 objects that are not threadsafe, say in a connection pool, you could use a Semaphore with 5 permits to make sure that the number of threads interacting with the pool is <= the total resources.

An alternative to reducing the semaphore permits to 1 is to call
```java
semaphore.acquire(200);
semaphore.release(200);
```
to acquire and release all of the available permits atomically.