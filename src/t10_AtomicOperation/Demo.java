package t10_AtomicOperation;

import java.util.concurrent.atomic.AtomicInteger;

//原子操作
public class Demo {
    private AtomicInteger val = new AtomicInteger(0);

    public int getVal(){
        return val.incrementAndGet();
    }
    public static void main(String[] args) {
        Demo d = new Demo();
        for(int i = 0; i < 3; i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true){
                        System.out.println(Thread.currentThread().getName() + " value is " + d.getVal());
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }
    }
}
