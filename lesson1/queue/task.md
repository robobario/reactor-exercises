# Queues

Another useful set of concurrent Classes are the BlockingQueues and BlockingDeques (deque is a double-ended-queue where you can push/pop from the head or tail)

You can make a fixed size queue that can have stuff pushed onto it from multiple threads. You can call methods to push that will throw an exception if it's full, or call methods that will return a boolean false if the queue was full.

Then on the consumer side you can poll it with blocking or non-blocking calls.

## Task

Change the future to call the blocking `take` method of the queue, this should block until the runnable pushes a value to the queue.

## Note

Queues can provide a way to build backpressure into an app. If you have some threads that are producing a lot of work then a queue can be a place to block, and prevent endless stuff accumulating in memory. Instead of pushing unlimited 'things' into memory your producers can block when pushing to the queue.

Most of the time we are just working with futures and things like queueing are left up to frameworks like the ExecutorService or reactor.