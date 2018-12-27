package lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockExample {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        Map<String, String> map = new HashMap<>();

        ReadWriteLock lock = new ReentrantReadWriteLock();

        Runnable writeTask = () -> {
            System.out.println("Try to write");
            lock.writeLock().lock();
            try {
                System.out.println("Start write");

                sleep(3);
                map.put("foo", "bar");

                System.out.println("Finish write");
            } finally {
                lock.writeLock().unlock();
            }
        };

        Runnable readTask = () -> {
            System.out.println("Try to read");
            lock.readLock().lock();
            try {
                System.out.println("Start read");

                System.out.println(map.get("foo"));
                sleep(3);

                System.out.println("Finish read");
            } finally {
                lock.readLock().unlock();
            }
        };

        executor.submit(writeTask);

        executor.submit(readTask);
        executor.submit(readTask);

        executor.shutdown();
    }

    private static void sleep(int secs) {
        try {
            Thread.sleep(TimeUnit.SECONDS.toMillis(secs));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
