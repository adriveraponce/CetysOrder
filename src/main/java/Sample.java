import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.Executors;

public class Sample {
    public static void fetch(int index) {
        System.out.println(index + " entering " + Thread.currentThread());
        try {
            var count = Files.lines(Path.of("Sample.java")).count();
            //Thread.sleep(10_000);
        } catch (Exception ex) {
            //intentionally ignoring
        }
        System.out.println(index + " exiting " + Thread.currentThread());
    }

    public static void main(String[] args) {
        var MAX = 10;

        //try (var executorService = Executors.newFixedThreadPool(MAX)) {
        try(var executorService = Executors.newVirtualThreadPerTaskExecutor()) {
            for (var i = 0; i < MAX; i++) {
                var index = i;

                executorService.submit(() -> fetch(index));
            }
        }
    }
}

//javac Sample.java
//java Sample | sort
//observe the threads
//Please comment out the line with newFixedThreadPool
//Uncomment the line with newVirtualThreadPerTaskExecutor
//
//java Sample | sort
//observe the threads