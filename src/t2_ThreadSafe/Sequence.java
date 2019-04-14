package t2_ThreadSafe;

// 从java bytecode 角度看线程安全问题
public class Sequence {

    private int value;

    public int next(){
        return value++;
    }

    public static void main(String[] args) {
        Sequence s = new Sequence();
        for (int i = 0; i < 3; i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true){
                       System.out.println(s.next());
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {

                        }
                    }
                }
            }).start();
        }
    }
}
