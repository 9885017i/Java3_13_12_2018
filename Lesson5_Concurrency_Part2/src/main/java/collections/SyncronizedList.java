package collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class SyncronizedList {

    private static AtomicInteger value = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
//        List<String> data = new ArrayList<>();
//        List<String> data = Collections.synchronizedList(new ArrayList<>());
        List<String> data = new CopyOnWriteArrayList<>();

        createProducer(data).start();
        createProducer(data).start();

        createReader(data).start();

        Thread.sleep(5000);
    }

    private static Thread createReader(List<String> data) {
        Thread reader = new Thread(() -> {
            while (true) {
//                List<String> snapshot = new ArrayList<>();
//
//                synchronized (data) {
//                    snapshot.addAll(data);
//                }

                for (String datum : data) {
                    System.out.print(datum);
                }

                System.out.println();

                sleep(10);
            }
        });
        reader.setDaemon(true);
        return reader;
    }

    private static Thread createProducer(List<String> data) {
        Thread producer = new Thread(() -> {
           while (true) {
               data.add(value.incrementAndGet() + " ");
               sleep(10);
           }
        });
        producer.setDaemon(true);
        return producer;
    }


    private static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
