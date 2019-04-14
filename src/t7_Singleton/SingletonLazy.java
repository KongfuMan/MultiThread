package t7_Singleton;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//单例模式与线程安全问题
// 多个线程访问getInstace时存在线程安全问题
public class SingletonLazy {
    private SingletonLazy() {
    }

    //  volatile关键字防止指令重排（jvm的优化方式)
    private static volatile SingletonLazy instance;

    public static  SingletonLazy getInstance(){
        if (instance == null){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized(SingletonLazy.class){
                if (instance == null){
                    instance = new SingletonLazy();
                }

            }
            return instance;
        }

        return instance;
    }

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(20);
        for (int i = 0; i < 20; i++){
            service.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println(SingletonLazy.getInstance());
                }
            });
        }
        service.shutdown();
    }

}
