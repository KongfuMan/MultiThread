package producer_and_consumer.semaphore;

import producer_and_consumer.semaphore.Cache;

public class Consumer implements Runnable {
    private Cache cache;

    public Consumer(Cache cache){
        this.cache = cache;
    }

    @Override
    public void run() {
        while(true){
            try {
                cache.consume();
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
