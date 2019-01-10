package dz;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class CarService {

    private final CyclicBarrier waitForPrepareBarrier;
    private FinishNotifier finishNotifier;
    private CountDownLatch raceStartNotifier;

    public CarService(int carCount, FinishNotifier finishNotifier, CountDownLatch raceStartNotifier) {
        this.waitForPrepareBarrier = new CyclicBarrier(carCount + 1);
        this.finishNotifier = finishNotifier;
        this.raceStartNotifier = raceStartNotifier;
    }

    public Car createCar(Race race, int speed) {
        return new Car(race, speed, waitForPrepareBarrier, raceStartNotifier, finishNotifier);
    }

    public void awaitingAllCarsStarted() {
        try {
            waitForPrepareBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }


}
