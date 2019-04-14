package writer_and_reader.Sempaphore;

import java.util.concurrent.Semaphore;

public class ReaderFirst {
    private int count = 0;  //缓存内容
    private Semaphore writer = new Semaphore(1);   //写者与任何其他人都互斥
    private Semaphore reader  = new Semaphore(1);

    public void write() throws InterruptedException {
        writer.acquire();
        count++;
        System.out.println("Write current count is " + count);
        writer.release();
    }

    //读者与写者互斥，与其他读者不互斥
    public void read() throws InterruptedException {
        //如何判断当前有没有写着呢？

    }

    private static class TaskA implements Runnable{
        private ReaderFirst readerFirst;

        public TaskA(ReaderFirst readerFirst) {
            this.readerFirst = readerFirst;
        }

        @Override
        public void run() {
            try {
                readerFirst.read();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static class TaskB implements Runnable{
        private ReaderFirst readerFirst;

        public TaskB(ReaderFirst readerFirst) {
            this.readerFirst = readerFirst;
        }

        @Override
        public void run() {
            try {
                readerFirst.write();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ReaderFirst readerFirst = new ReaderFirst();
        for (int i = 0; i < 3; i++){
            new Thread(new TaskA(readerFirst)).start();
        }
        for (int i = 0; i < 4; i++){
            new Thread(new TaskB(readerFirst)).start();
        }
    }
}
