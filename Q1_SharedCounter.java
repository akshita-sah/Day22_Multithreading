public class Q1_SharedCounter {
    private static int counter = 0;
    private static int NUM_OF_THREADS = 5;
    public static void main(String[] args) {
        Thread[] threads = new Thread[NUM_OF_THREADS];
        for (int i = 0; i < NUM_OF_THREADS; i++) {
            threads[i] = new IncrementThread();
            threads[i].start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Final Counter Value: " + counter);
    }
    private static class IncrementThread extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 1000; i++) {
                    counter++;
            }
        }
    }
}
