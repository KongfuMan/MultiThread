package writer_and_reader.Sempaphore;

import java.util.concurrent.Semaphore;

//这是错误的版本❌
public class ReaderFirstWrong {
    private int count = 0;  //缓存内容
    private Semaphore rw = new Semaphore(1);   //读者与写者互斥
    private Semaphore ww = new Semaphore(1);   //写者之间互斥

    public void write() throws InterruptedException {
        rw.acquire();
        ww.acquire();
        count++;
        System.out.println("Write current count is " + count);
        if (rw.hasQueuedThreads()){
            rw.release();   //如果有读者等待，则读者写者信号量加一
        }
        ww.release();
    }

    public void read() throws InterruptedException {
        rw.acquire();   // 错了，这里变成了读者之间互斥
        //do reading
        System.out.println("Read current count is " + count);
        rw.release();
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
