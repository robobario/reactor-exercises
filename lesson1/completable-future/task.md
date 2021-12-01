# CompletableFuture

The next thing we really want is to get the result of some async computation and use it. For example you might want to make two asynchronous HTTP requests in parallel and then combine their results. How do you reliably collect both results and use them? 

You could use Atomics, and thread joining or some other signalling, push results into shared queues or some other signalling to implement this, but then there is a lot to consider. How do you handle timeouts? How do you handle exceptions? Java provides nice APIs so you don't have to reinvent the wheel.

In Java 1.8 they introduced [CompletableFuture](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/CompletableFuture.html).

A CompletableFuture represents some future result that some other thread can complete. You can block on the Future's methods like `get` and when some other thread completes it the `get` will return the result to the caller. It provides structured ways to handle timeouts and exceptions.

## Task

The first task is to complete a CompletableFuture from a Thread. Complete `future` with `result`.