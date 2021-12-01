package task;

import java.util.stream.IntStream;

public class Task {

    public long parallel() {
        return IntStream.range(0, 2).mapToObj(i -> {
            String name = Thread.currentThread().getName();
            System.out.println("thread name: " + name);
            return name;
        }).distinct().count();
    }

    public long sequential() {
        return IntStream.range(0, 2).mapToObj(i -> Thread.currentThread().getName()).distinct().count();
    }

}