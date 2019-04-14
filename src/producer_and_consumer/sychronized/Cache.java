package producer_and_consumer.sychronized;


public class Cache {
    private final static int MAX_SIZE = 100;
    private volatile int cacheSize = 0;

    public Cache(){
        cacheSize = 0;
    }

    public Cache(int size){
        cacheSize = size;
    }

    public synchronized int getCurrentSize(){
        return cacheSize;
    }

    public void produce(){
        synchronized (this){
            while (cacheSize >= MAX_SIZE ){
                try {
                    System.out.println("缓存已满，生产者需要等待");
                    wait(); //进入WAITING状态，释放锁
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            cacheSize++;
            System.out.println("生产了一个产品。当前产品数量为"+ cacheSize);
            notifyAll();
        }

    }

    public void consume(){
        synchronized (this){
            while(cacheSize <= 0){
                try {
                    System.out.println("缓存为空，消费者需要等待");
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            cacheSize--;
            System.out.println("消费了一个产品。当前产品数量为"+ cacheSize);
            notifyAll();
        }
    }
}
