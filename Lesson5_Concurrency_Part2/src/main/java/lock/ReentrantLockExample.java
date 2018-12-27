package lock;

import javax.imageio.plugins.tiff.TIFFImageReadParam;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockExample {

    private static int count = 0;

    public static void main(String[] args) {
//        testLock();
        testTryLock();
    }

    private static void testLock() {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        ReentrantLock lock = new ReentrantLock();

        executor.submit(() -> {

            lock.lock();
                try {
                    System.out.println("Locked: " + lock.isLocked());
                    System.out.println("Held by me: " + lock.isHeldByCurrentThread());
                    sleep(1);
                } finally {
                    lock.unlock();
                }
        });

        executor.submit(() -> {
            System.out.println("Locked: " + lock.isLocked());
            System.out.println("Held by me: " + lock.isHeldByCurrentThread());
            boolean locked = lock.tryLock();
            System.out.println("Lock acquired: " + locked);
        });

        executor.shutdown();
    }

    private static void testTryLock() {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        ReentrantLock lock = new ReentrantLock();

        CyclicBarrier cb = new CyclicBarrier(2);

        executor.submit(() -> {
            await(cb);

            if (lock.tryLock()) {
                try {
                    count++;
//                    sleep(1);
                    System.out.println(count);
                } finally {
                    lock.unlock();
                }
            }
        });

        executor.submit(() -> {
            await(cb);
            if (lock.tryLock()) {
                    try {
                        count++;
//                    sleep(1);
                        System.out.println(count);
                    } finally {
                        lock.unlock();
                    }
            }
        });

//        executor.submit(() -> {
//            System.out.println("Locked: " + lock.isLocked());
//            System.out.println("Held by me: " + lock.isHeldByCurrentThread());
//            boolean locked = lock.tryLock();
//            System.out.println("Lock acquired: " + locked);
//        });

        executor.shutdown();
    }

    private static void await(CyclicBarrier cb) {
        try {
            cb.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    private static void sleep(int secs) {
        try {
            Thread.sleep(TimeUnit.SECONDS.toMillis(secs));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
