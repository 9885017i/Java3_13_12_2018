package homework.task3;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Task3 {

    public static void main(String[] args) throws InterruptedException {

        MFP mfp = new MFP();
        ScheduledExecutorService printService = Executors.newScheduledThreadPool(3, new ThreadFactoryBuilder()
                .setNameFormat("Print - %s")
                .build());

        ScheduledExecutorService scanService = Executors.newScheduledThreadPool(3, new ThreadFactoryBuilder()
                .setNameFormat("Scan - %s")
                .build());

        printService.scheduleAtFixedRate(mfp::print, 0, 50, TimeUnit.MILLISECONDS);
        printService.scheduleAtFixedRate(mfp::print, 0, 50, TimeUnit.MILLISECONDS);
        printService.scheduleAtFixedRate(mfp::print, 0, 50, TimeUnit.MILLISECONDS);

        scanService.scheduleAtFixedRate(mfp::scan, 0, 50, TimeUnit.MILLISECONDS);
        scanService.scheduleAtFixedRate(mfp::scan, 0, 50, TimeUnit.MILLISECONDS);
        scanService.scheduleAtFixedRate(mfp::scan, 0, 50, TimeUnit.MILLISECONDS);

        Thread.sleep(200);

        printService.shutdown();
        scanService.shutdown();
    }
}
