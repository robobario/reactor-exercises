package task;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Tests {

    @Test
    public void testSolution() {
        Task task = new Task();
        Assertions.assertEquals("interrupted", task.run());
    }
}