package utils;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreDemo {

    public static void main(String[] args) {
        Semaphore smp = new Semaphore(2);

        ExecutorService executorService = Executors.newCachedThreadPool(new ThreadFactoryBuilder()
                .setNameFormat("Thread - %s")
                .build());

        for (int i = 0; i < 10; i++) {
            executorService.execute(() -> createTask(smp));
        }

        executorService.shutdown();
    }

    private static void createTask(Semaphore smp) {
        try {
            System.out.println(Thread.currentThread().getName() + " перед семафором");
            smp.acquire();
            System.out.println(Thread.currentThread().getName() + " получил доступ к ресурсу");
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + " освободил ресурс");
            smp.release();
        }

    }
}
