package task;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Tests {

    @Test
    public void testSolution() {
        Task task = new Task();
        int numThreads = 200;
        int incrementsPerThread = 1000000;
        Assertions.assertEquals(numThreads * incrementsPerThread, task.run(numThreads, incrementsPerThread));
    }
}