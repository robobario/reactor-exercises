# Atomic Integer

From the [tutorial](https://docs.oracle.com/javase/tutorial/essential/concurrency/atomic.html)
> In programming, an atomic action is one that effectively happens all at once. An atomic action cannot stop in the middle: it either happens completely, or it doesn't happen at all. No side effects of an atomic action are visible until the action is complete.

The JDK provides some high level concurrency classes that make some of this stuff easier/faster/better.

As we've seen, synchronisation has a cost and there are other strategies apart from telling the JVM to not interleave those calls. If you synchronise in the wrong place you could unintentionally limit our application.

One thing they offer is the family of Atomic classes that provide unsyncronised classes that give you atomic methods to do things like increment an integer without the race condition we saw from using an int variable.

## Task

Modify the `Counter` class to use an <a href="psi_element://java.util.concurrent.atomic.AtomicInteger">AtomicInteger</a> field instead of an int. This will allow us to atomically increment without the race condition.