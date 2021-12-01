
# Threads - Interrupts
> An interrupt is an indication to a thread that it should stop what it is doing and do something else. It's up to the programmer to decide exactly how a thread responds to an interrupt, but it is very common for the thread to terminate. This is the usage emphasized in this lesson.

# Task

Interrupt the thread using Thread.interrupt before joining.

## Notes
This will cause Thread.sleep() to throw an InterruptedException. Many blocking IO type methods will also throw similar exceptions if an interrupt is received while blocked.

Why is this useful: you may wish to cancel long-running work cleanly (or cleanup on app shutdown). You can do cleanup work from your `catch`, like flushing writes and closing resources etc before you terminate the thread.