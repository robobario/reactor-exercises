# CompletableFuture and executors

CompletableFuture provide apis for running work on executors asynchronously.

The `runAsync(Runnable runnable, Executor executor)` and `supplyAsync(Supplier<U> supplier, Executor executor)` methods of `CompletableFuture` are our entrypoint to submit a Runnable or Callable for async execution on a given Executor.

They also offer methods `runAsync(Runnable runnable)` and `supplyAsync(Supplier<U> supplier)` which will be executed on a default executor (it depends on your java runtime what this will be, commonly it's a shared ForkJoinPool)
## Task

Change which executor one of the Suppliers is run on, this means the suppliers will be executed in different threads.

## Note

Third party libraries often return CompletableFuture, so you may not need to kick off the chain with a runAsync or runSupplier yourself. They are responsible for creating the future and completing it somehow.

But you can control where the results are evaluated by using methods like `thenComposeAsync`, `thenApplyAsync` etc, to switch the downstream work to a different thread or pool of threads.

Maybe you are working with a CompletableFuture returned from an HttpClient and you want to do some heavy computation when the response is received. It might be wise to shift this work to an executor because the Future could be being completed from something like a netty event loop thread. The work is being initiated by the same thread reading bytes from some socket and if you block that particular thread you may block up network operations for other users of the client.

So you want to be aware which thread is completing a future and avoid blocking event loops in tech like our couchbase or http clients.