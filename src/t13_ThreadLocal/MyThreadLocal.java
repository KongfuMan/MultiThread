package t13_ThreadLocal;

public class MyThreadLocal {

    //为每一个线程创建了一个threadlocal私有变量
    private ThreadLocal<Integer> count = new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {
            return new Integer(0);
        }
    };

    public int getNext(){
        Integer value = count.get();
        value++;
        count.set(value);
        return value;
    }

    public static void main(String[] args) {
        MyThreadLocal t = new MyThreadLocal();
        for (int i = 0; i < 3; i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while(true){
                        System.out.println(Thread.currentThread().getName() + "  " +t.getNext());
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
