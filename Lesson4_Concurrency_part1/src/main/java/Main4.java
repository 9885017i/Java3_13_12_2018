import java.util.concurrent.*;

public class Main4 {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

//
//        Thread thread = new Thread(() -> {
//            System.out.println("Hello!");
//            System.out.println("2:" + Thread.currentThread().getName());
//        }, "MyThread");
////
////
//        thread.start();
//        System.out.println("1:" + Thread.currentThread().getName());
//        if (true) {
//            return;
//        }
//        Thread t = new Thread(new MyRunnable());
//
//        new MyThread().start();

//        testThread();

        testExecutorService();
    }

    private static void testThread() throws InterruptedException, ExecutionException {
        Thread t = new Thread(() -> {
            System.out.println(Thread.currentThread().getName());
            try {
                Thread.sleep(5000L);
                System.out.println("Finish " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "MyThread");


        System.out.println("State: " + t.getState());
        t.setDaemon(true);
        t.setPriority(Thread.MIN_PRIORITY);

        t.start();

        t.resume();
        t.suspend();
        t.stop();

        System.out.println("State: " + t.getState());

        Thread.sleep(3000);


        System.out.println("State: " + t.getState());
        System.out.println(t.getPriority());
        t.join();
        System.out.println("State: " + t.getState());
    }

    private static void testExecutorService() throws InterruptedException, ExecutionException {
        ExecutorService service = Executors.newSingleThreadExecutor();

//        service.execute(() -> System.out.println(Thread.currentThread().getName()));
        Future<String> future = service.submit(() -> Thread.currentThread().getName());

        String s = future.get();
        System.out.println("Future value is " + s);


        System.out.println("Finish " + Thread.currentThread().getName());

        service.shutdown();
    }
}
