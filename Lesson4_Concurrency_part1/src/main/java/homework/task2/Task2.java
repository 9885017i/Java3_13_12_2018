package homework.task2;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Task2 {

    public static final int LINE_COUNT = 10;

    public static void main(String[] args) throws IOException, InterruptedException {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(3);

        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("task2.txt")))) {
            executorService.scheduleAtFixedRate(() -> writeToFile("aaaaa", out), 1, 20, TimeUnit.MILLISECONDS);
            executorService.scheduleAtFixedRate(() -> writeToFile("bbbbb", out), 2, 20, TimeUnit.MILLISECONDS);
            executorService.scheduleAtFixedRate(() -> writeToFile("ccccc", out), 3, 20, TimeUnit.MILLISECONDS);
            Thread.sleep(1000);
        }
        executorService.shutdown();
    }


    private static void writeToFile(String textLine, PrintWriter out) {
            for (int i = 0; i < LINE_COUNT; i++) {
                out.println(textLine);
            }
    }

}
