package task;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Tests {

    @Test
    public void testSolution() {
        Assertions.assertEquals("called from main,called from thread", new Task().get());
    }
}