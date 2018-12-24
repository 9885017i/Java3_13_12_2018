package concurrency.queue;

public class Producer implements Runnable {

    private Queue queue;
    private boolean stop;
    public final Thread thread;

    public Producer(Queue queue) {
        this.queue = queue;
        thread = new Thread(this, "Producer ");
//        thread.setDaemon(true);
        thread.start();
    }

    @Override
    public void run() {
        int i = 0;
//        while ( true ) {
//        while ( !stop ) {
        while ( !Thread.currentThread().isInterrupted() ) {
            queue.put(i++);
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
