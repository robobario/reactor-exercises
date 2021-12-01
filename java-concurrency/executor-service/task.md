# Executor

Having learned something about Threads, now we want to learn how to avoid working with them directly.

Managing where your work runs and providing efficient reuse of threads is something the JDK provides for us with some high level constructs.

The <a href="psi_element://java.util.concurrent.Executor">Executor</a> is an abstraction which you can tell to execute a runnable. We do not have to fuss around with creating and starting threads and we can use different Executor implementations to control our execution strategy.

For example we may want the <a href="psi_element://java.util.concurrent.Executor">Executor</a> to put our runnable in a queue and execute it on a single thread. So all runnables submitted to it will be executed on this single thread.

Or we may want the <a href="psi_element://java.util.concurrent.Executor">Executor</a> to manage a pool of threads and manage farming out our runnable to a Thread when it is availble.

Or we might want it to just directly run our runnable in the calling thread, useful when testing.

The executor abstracts away how we execute it and allows us to plug in different strategies.

## Task

Update `Task` so that it executes the runnable with the executor. This is the same as the very first task using Threads directly, just going through a service.