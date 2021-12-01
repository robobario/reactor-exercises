# Atomic Reference

Sometimes it's good to do things the hard way. AtomicInteger and AtomicLong supply nice atomic methods for incrementing numbers. But you could also use an `AtomicReference<Integer>`. This is useful if you want to work with some class that isn't a simple number.

The AtomicReference has a method `V getAndUpdate(UnaryOperator<V> updateFunction)` which will update the reference optimistically.

with docs:
> Atomically updates (with memory effects as specified by VarHandle.compareAndSet) the current value with the results of applying the given function, returning the previous value. The function should be side-effect-free, since it may be re-applied when attempted updates fail due to contention among threads.

So it's an optimistic locking strategy, it gets the currently referenced object, applies the function to it to get the new value and then tries to set it. If another thread modified it in the meanwhile then this fails and it will attempt to do the same getAndUpdate logic again with the new value.

## Task

Modify the `Counter` class to use an AtomicReference<Integer> field instead of an int and implement `getAndUpdate`. This should cause each increment to be applied atomically and satisfy our test.

## Notes

This strategy was slower than synchronising the method on my machine, heavy optimistic locking maybe not so great under heavy contention, it will incur a lot of retries. So your machine may spin for a while :)