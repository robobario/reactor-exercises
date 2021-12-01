# Countdown Latch

Another high level concurrency class available for coordination is the <a href="psi_element://java.util.concurrent.CountDownLatch">CountDownLatch</a>.

> A synchronization aid that allows one or more threads to wait until a set of operations being performed in other threads completes.
>
> A CountDownLatch is initialized with a given count. The await methods block until the current count reaches zero due to invocations of the countDown() method, after which all waiting threads are released and any subsequent invocations of await return immediately. This is a one-shot phenomenon -- the count cannot be reset. If you need a version that resets the count, consider using a CyclicBarrier.

## Task

Update `Task` so that the countdown latch is counted down after the string has been set.

## Notes

This is simulating one purpose of the latch, you can use it as a gate to prevent multiple threads from accessing some resource until the countdown latch is counted to zero. 

So if we are waiting on 5 DSP responses before continuing with some processing you could use a latch with count 5 and have a response-processing thread await on the latch.

Beware awaiting without a timeout :)