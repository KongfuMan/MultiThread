package producer_and_consumer.semaphore;

import producer_and_consumer.semaphore.Cache;
import producer_and_consumer.semaphore.Consumer;
import producer_and_consumer.semaphore.Producer;

public class Main {
    public static void main(String[] args) {
        Cache cache = new Cache(10);

        Producer p = new Producer(cache);
        Consumer c = new Consumer(cache);

        int producerCount = 1, consumerCount = 4;
        for (int i = 0; i < producerCount; i++){
            new Thread(p).start();
        }
        for (int i = 0; i < consumerCount; i++){
            new Thread(c).start();
        }
    }
}
