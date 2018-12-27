package utils;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BarrierExample {


    private static final int THREADS_COUNT = 6;


    public static void main(String[] args) {
        CyclicBarrier cb = new CyclicBarrier(THREADS_COUNT);

        ExecutorService executorService = Executors.newCachedThreadPool(new ThreadFactoryBuilder()
                .setNameFormat("Thread - %s")
                .build());

        for (int i = 0; i < THREADS_COUNT; i++) {
            executorService.execute(() -> createTask(cb));
        }

        executorService.shutdown();
    }

    private static void createTask(CyclicBarrier cb) {
            try {
                System.out.println(Thread.currentThread().getName() + " готовится");
                Thread.sleep(100 + (int) (3000 * Math.random()));
                System.out.println(Thread.currentThread().getName() + " готов");

                cb.await();

                System.out.println(Thread.currentThread().getName() + " запустился");

            } catch (Exception e) {
                e.printStackTrace();
            }
    }
}
