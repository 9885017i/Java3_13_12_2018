package concurrency.queue;

public class Consumer implements Runnable {

    private Queue queue;
    private boolean stop;
    public final Thread thread;

    public Consumer(Queue queue) {
        this.queue = queue;
        thread = new Thread(this, "Consumer ");
//        thread.setDaemon(true);
        thread.start();
    }


    @Override
    public void run() {
//        while ( true ) {
//        while ( !stop ) {
        while ( !Thread.currentThread().isInterrupted() ) {
            queue.get();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                return;
            }
        }
        synchronized (queue) {
            queue.notifyAll();
        }
    }

    public void stop() {
        this.stop = true;
    }
}
