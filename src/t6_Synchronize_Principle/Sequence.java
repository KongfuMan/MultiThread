package t6_Synchronize_Principle;

// Synchronized 从字节码角度 看原理
public class Sequence {

    private int value;

    /**
     * Synchronized 是一个互斥锁， 放在普通方法上，内置锁就是当前类的实例
     * @return
     */
    public synchronized int next(){
        return value++;
    }

    public static int statVal;
    /**
     * Synchronized 修饰静态方法时，内置锁就是当前的class字节码对象 Sequence.class
     * @return
     */
    public synchronized static int nextVal(){
        return statVal++;
    }

    public int codeBlock(){
        /**
         * synchronized 修饰代码块，内置锁必须是一个对象，可以使this,也可是Integer.valueOf(value)
         * 也可以是Sequence.class
         */
        synchronized (this){
            if (value > 0){
                return 1;
            }else{
                return 2;
            }
        }
    }

    public static void main(String[] args) {
        Sequence s = new Sequence();
        for(int i = 0; i < 2; i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true){
                        System.out.println(s.next());
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }
    }
}
