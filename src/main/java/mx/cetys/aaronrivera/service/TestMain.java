package mx.cetys.aaronrivera.service;

import org.springframework.boot.ApplicationRunner;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentSkipListSet;

public class TestMain {

    public static void main(String[] arg) throws InterruptedException {

        // Jose Paumard, Oracle
        var threads = new ArrayList<Thread>();
        var names = new ConcurrentSkipListSet<String>();

        for (var i = 0; i < 1000; i++) {
            var first = i == 0;
            var t = Thread.ofVirtual()
                    .unstarted(
                            () -> {
                                if (first) names.add(Thread.currentThread().toString());
                                try {
                                    Thread.sleep(100);
                                } catch (InterruptedException e) {
                                    throw new RuntimeException(e);
                                }
                                if (first) names.add(Thread.currentThread().toString());
                                try {
                                    Thread.sleep(100);
                                } catch (InterruptedException e) {
                                    throw new RuntimeException(e);
                                }
                                if (first) names.add(Thread.currentThread().toString());
                                try {
                                    Thread.sleep(100);
                                } catch (InterruptedException e) {
                                    throw new RuntimeException(e);
                                }
                                if (first) names.add(Thread.currentThread().toString());
                                try {
                                    Thread.sleep(100);
                                } catch (InterruptedException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                    );
            threads.add(t);
        }
        for (var t : threads) t.start();
        for (var t : threads) t.join();
        System.out.println("Aaron Threads " + names);
    }
}
