package producer_and_consumer.sychronized;

public class Producer implements Runnable {
    private Cache cache;

    public Producer(Cache cache){
        this.cache = cache;
    }

    @Override
    public void run() {
        while(true){
            cache.produce();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
