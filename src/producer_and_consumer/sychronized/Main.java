package producer_and_consumer.sychronized;

public class Main {
    public static void main(String[] args) {
        Cache cache = new Cache(10);

        Producer p = new Producer(cache);
        Consumer c = new Consumer(cache);

        int producerCount = 4, consumerCount = 4;
        Thread[] ps = new Thread[producerCount];
        Thread[] cs = new Thread[consumerCount];
        for (int i = 0; i < producerCount; i++){
            ps[i] = new Thread(p);
        }
        for (int i = 0; i < consumerCount; i++){
            cs[i] = new Thread(c);
        }
        for (int i = 0; i < producerCount; i++){
            ps[i].start();
        }
        for (int i = 0; i < consumerCount; i++){
            cs[i].start();
        }
    }
}
