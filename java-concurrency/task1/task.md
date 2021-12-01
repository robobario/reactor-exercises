# Threads

> A thread is a thread of execution in a program. The Java Virtual Machine allows an application to have multiple threads of execution running concurrently.

The oldest way to execute code concurrently in Java is the [Thread](https://docs.oracle.com/javase/7/docs/api/java/lang/Thread.html) (I think!?)

To work directly with a thread you construct one, passing it a <a href="psi_element://java.lang.Runnable">Runnable</a>, then start the thread.

## Task

Start the thread in [Task](course://lesson1/task1/src/task/Task.java) so that it adds the string `called from thread` to the queue. 

## Notes
Note that the thread sleeps for 2 seconds first so it will most likely enter the queue after `called from main`. So you can see from this that starting the thread did not prevent the main thread from continuing with it's work. Nothing is guaranteed of course :)

Another risky thing we have done is used an ArrayList to collect the strings. Many of the standard collections like this will not be threadsafe. For example, if you iterate over the ArrayList and a concurrent modification is made from another thread, a `ConcurrentModificationException` will be thrown from the iterator method.

There are specialised collections available to avoid these kind of issues.