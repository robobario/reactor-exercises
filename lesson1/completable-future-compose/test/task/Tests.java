package task;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Tests {

    @Test
    public void testSolution() {
        Assertions.assertEquals("11", new Task().get());
    }
}