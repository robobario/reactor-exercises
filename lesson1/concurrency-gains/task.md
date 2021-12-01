# Threads - Why

Eventually most of our applications want to do some IO, like waiting on a network socket for some bytes to be sent or received. Or trying to sync a file to disk and waiting for the write to succeed.

If we are using blocking APIs then our thread will just be lazing around waiting for some IO to complete. If we were trying to do all the work from that one thread it would be bottlenecked on IO. We can use multiple threads so that while Thread X is waiting on IO, the machine can decide to run Thread Y.

Similarly we have multiple cores on modern computers. One thread of execution can only max out one of those cores. So to use our available resources nicely we can use multiple threads to access multiple cores concurrently.

## Task

Modify the `concurrent` method of [Task](course://lesson1/concurrency-gains/src/task/Task.java) to do the two sleeps in two different Threads (and join them). This should complete more quickly than the two consecutive sleeps.

## Notes

Again relying on sleeping is unreliable. Something you will find across all these async technologies is you cannot rely on order of execution, it is not guaranteed your thread will wake up and execute when you hoped.

This idea underpins some old implementations of HTTP servers. Like Tomcat long ago used (uses depending on configuration?) a Thread per connection from a client so that it could block on IO on many connections concurrently. Modern technology has provided alternatives to this model, but this was a good application for Threads long ago.

Threads have some cost associated with them in scheduling and stack memory, and managing access to the shared resources of the process. So there are lots of techniques and technologies to reduce and reuse threads efficiently.