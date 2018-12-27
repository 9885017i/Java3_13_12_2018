package utils;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimpleCDL {

    private static final int THREADS_COUNT = 6;

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool(new ThreadFactoryBuilder()
                .setNameFormat("Thread - %s")
                .build());

        final CountDownLatch cdl = new CountDownLatch(THREADS_COUNT);

        for (int i = 0; i < THREADS_COUNT; i++) {
            executorService.execute(() -> createTask(cdl));
        }

        System.out.println("Начинаем");

        try {
            // пока счетчик не приравняется нулю, будем стоять на этой строке
            //Threads.forEach(thread -> thread.join())
            cdl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // как только все потоки выполнили свои задачи - пишем сообщение
        System.out.println("Работа завершена");
        executorService.shutdown();
    }

    private static void createTask(CountDownLatch cdl) {
        try {
            // считаем, что выполнение задачи занимает ~1 сек
            Thread.sleep(500 + (int) (500 * Math.random()));
            System.out.println(Thread.currentThread().getName() + " - готов");
            // как только задача выполнена, уменьшаем счетчик
            cdl.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}