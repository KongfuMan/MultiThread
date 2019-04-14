package t9_volatile;


public class Demo {
    private volatile int value = 2;

    public void setA(int a){
        value += 10;
    }

    public int getA(){
        return value;
    }

    public static void main(String[] args) {
        Demo d = new Demo();
        new Thread(new Runnable() {
            @Override
            public void run() {
//                try {
//                    Thread.sleep(10);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                System.out.println(Thread.currentThread().getName() + " 中a的值为 " +  d.getA());
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
//                try {
//                    Thread.sleep(10);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                d.setA(10);
                System.out.println(Thread.currentThread().getName() + " set a++ ");
            }
        }).start();

    }
}
