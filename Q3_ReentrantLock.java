import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Q3_ReentrantLock {
    private static final int NUM_THREADS = 3;
    private static final int ITERATIONS = 1000;
    private static int sharedCounter = 0;
    private static Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        for (int i = 0; i < NUM_THREADS; i++) {
            Thread thread = new Thread(new Worker());
            thread.start();
        }
    }
    static class Worker implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < ITERATIONS; i++) {
                try {
                    lock.lock();
                    // Critical section: Access the shared resource
                    sharedCounter++;
                    System.out.println(Thread.currentThread().getName() + " incremented the counter to: " + sharedCounter);

                } finally {
                    lock.unlock();
                }
                try {
                    Thread.sleep((long) (Math.random() * 10));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
