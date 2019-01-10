package dz;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainClass {
    public static final int CARS_COUNT = 4;

    public static void main(String[] args) {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");

        FinishNotifier finishNotifier = new FinishNotifier(CARS_COUNT);
        CountDownLatch raceStartNotifier = new CountDownLatch(1);


        ExecutorService executorService = Executors.newFixedThreadPool(CARS_COUNT);

        CarService carService = new CarService(CARS_COUNT, finishNotifier, raceStartNotifier);

        Race race = new Race(new Road(60), new Tunnel(CARS_COUNT), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = carService.createCar(race, 20 + (int) (Math.random() * 10));
        }

        for (Car car : cars) {
            executorService.execute(car);
        }

        carService.awaitingAllCarsStarted();

        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
        raceStartNotifier.countDown();

        finishNotifier.waitWhileAllCarsFinished();
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");

        executorService.shutdown();
    }
}

