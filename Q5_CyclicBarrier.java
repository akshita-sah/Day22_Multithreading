import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Q5_CyclicBarrier {
    private static final int NUM_THREADS = 3;
    private static final CyclicBarrier barrier = new CyclicBarrier(NUM_THREADS, () -> System.out.println("Barrier released!"));
    public static void main(String[] args) {
        for (int i = 0; i < NUM_THREADS; i++) {
            Thread thread = new Thread(new Worker(i));
            thread.start();
        }
    }
    static class Worker implements Runnable {
        private final int threadId;
        Worker(int threadId) {
            this.threadId = threadId;
        }
        @Override
        public void run() {
            try {
                System.out.println("Thread " + threadId + " is doing some work.");
                Thread.sleep((long) (Math.random() * 3000));
                System.out.println("Thread " + threadId + " has reached the barrier.");
                barrier.await();
                System.out.println("Thread " + threadId + " is now continuing its work after the barrier.");
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
