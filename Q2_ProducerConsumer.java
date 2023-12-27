import java.util.LinkedList;

public class Q2_ProducerConsumer {
    private static final int MAX_BUFFER_SIZE = 5;
    private LinkedList<Integer> buffer = new LinkedList<>();

    public static void main(String[] args) {
        Q2_ProducerConsumer example = new Q2_ProducerConsumer();
        Thread producerThread = new Thread(() -> example.produce());
        Thread consumerThread = new Thread(() -> example.consume());
        producerThread.start();
        consumerThread.start();
    }

    public void produce() {
        while (true) {
            synchronized (this) {
                try {
                    while (buffer.size() == MAX_BUFFER_SIZE) {
                        System.out.println("Buffer is full. Producer is waiting...");
                        wait();
                    }
                    int item = (int) (Math.random() * 100);
                    buffer.add(item);
                    System.out.println("Produced: " + item);
                    notify();
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void consume() {
        while (true) {
            synchronized (this) {
                try {
                    while (buffer.isEmpty()) {
                        System.out.println("Buffer is empty. Consumer is waiting...");
                        wait();
                    }
                    int item = buffer.remove();
                    System.out.println("Consumed: " + item);
                    notify();
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
