package task;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Tests {

    @Test
    public void testSolution() {
        Task task = new Task();
        int numThreads = 200;
        Assertions.assertEquals("asynchronously initialized value", task.run());
    }
}