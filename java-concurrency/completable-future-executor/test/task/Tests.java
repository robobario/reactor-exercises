package task;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Tests {

    @Test
    public void testSolution() {
        Assertions.assertEquals("false", new Task().get(), "thread names supplied by both futures were the same");
    }
}