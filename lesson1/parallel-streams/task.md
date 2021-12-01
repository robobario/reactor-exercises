# Parallel Streams

Java 8 also added the [Streams](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html) api.

One nice feature of streams is you can take a stream and call `parallel()` on it to turn it into a parallel stream where operations like `map`, `forEach` etc are applied asynchronously on the common forkjoinpool.

Note that a stream is entirely parallel or sequential. You cannot do some parallel work in the stream and then change to sequential mode. You would have to collect the results and create a new stream to change modes.

# Task

Change the `parallel` method so that the stream is in parallel mode. The test is checking how many distinct threads were used during evaluation and the parallel stream _should_ use two.