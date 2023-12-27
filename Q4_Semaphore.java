import java.util.concurrent.Semaphore;

public class Q4_Semaphore {
    private static final int NUM_CONNECTIONS = 3;
    private static Semaphore semaphore = new Semaphore(NUM_CONNECTIONS);

    public static void main(String[] args) {
        for (int i = 1; i <= 5; i++) {
            Thread clientThread = new Thread(new Client(i));
            clientThread.start();
        }
    }

    static class Client implements Runnable {
        private int clientId;

        Client(int clientId) {
            this.clientId = clientId;
        }

        @Override
        public void run() {
            try {
                System.out.println("Client " + clientId + " is trying to acquire a connection.");
                semaphore.acquire();
                System.out.println("Client " + clientId + " has acquired a connection and is performing some work.");
                Thread.sleep((long) (Math.random() * 2000));
                System.out.println("Client " + clientId + " has released the connection.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }
        }
    }
}
