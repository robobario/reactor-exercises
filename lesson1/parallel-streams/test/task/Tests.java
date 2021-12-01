package task;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Tests {

    @Test
    public void testSolution() {
        Task task = new Task();
        Assertions.assertTrue(task.parallel() > new Task().sequential(), "more threads should have been used during evaluation of the parallel stream");
    }
}