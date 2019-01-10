package dz;

import java.util.concurrent.CountDownLatch;

public class FinishNotifier {

    private final CountDownLatch countDownLatch;

    public FinishNotifier(int carsCount) {
        this.countDownLatch = new CountDownLatch(carsCount);
    }

    public void waitWhileAllCarsFinished() {
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void notifyAboutFinished() {
        countDownLatch.countDown();
    }
}
