package producer_and_consumer.monitor;

import producer_and_consumer.monitor.Cache;
import producer_and_consumer.monitor.Consumer;
import producer_and_consumer.monitor.Producer;

public class Main {
    public static void main(String[] args) {
        Cache cache = new Cache();

        Producer p = new Producer(cache);
        Consumer c = new Consumer(cache);

        int producerCount = 4, consumerCount = 2;
        for (int i = 0; i < producerCount; i++){
            new Thread(p).start();
        }
        for (int i = 0; i < consumerCount; i++){
            new Thread(c).start();
        }
    }
}
