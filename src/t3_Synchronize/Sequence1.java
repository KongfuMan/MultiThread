package t3_Synchronize;

// Synchronized 原理
public class Sequence1 {

    private int value;
    private volatile Object lock = new Object();

    public int codeBlock(){
        /**
         * synchronized 修饰代码块，内置锁必须是一个对象，可以使this,也可是Integer.valueOf(value)
         * 也可以是Sequence.class
         * 记住： synchronized 获取的锁对象是堆里的对象，不是栈里的引用
         */
        synchronized (lock){
           System.out.println(Thread.currentThread().getName() + "enter the synchronized block");
           lock = new Object();
           while (true){

           }
        }
    }

    public static void main(String[] args) {
        Sequence1 s = new Sequence1();
        for (int i = 0; i < 10; i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    s.codeBlock();
                }
            }).start();
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
    }
}
