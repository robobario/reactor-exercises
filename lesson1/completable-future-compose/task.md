# CompletableFuture composition

CompletableFutures can be composed.

For example you can have two CompletableFutures and use `combinedFuture = future1.thenCombine(future2, combinerFunction)`. This will create a combined future that will execute the combinerFunction once both future1 and future2 have completed (regardless of the order they complete).

## Task

Use `thenCombine` to add together the results of `futureA` and `futureB`.

## Note
In the end we have a blocking `get` on the future to return to the synchronous world of the unit test. In real use we may not need to return to any synchronised context and can use methods like `thenAccept` or `thenApply` to run whatever side-effecty code we want without relying on a blocking `get` somewhere.