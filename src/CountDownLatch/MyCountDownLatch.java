package CountDownLatch;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

//对一个文本中所有的数字并行求和
//一行一个线程，然后三行汇总
public class MyCountDownLatch {
    private int[] nums; //保存当前行的所有数字

    public MyCountDownLatch(int lines){
        nums = new int[lines];
    }

    public void calc(String line, int index, CountDownLatch countDownLatch){
        String[] numStrings = line.split(",");
        int sum = 0;
        for (String s:numStrings){
            sum += Integer.parseInt(s);
        }

        nums[index] = sum;  //把当前行的和放到数组的指定位置
        System.out.println(Thread.currentThread().getName() + " 执行计算任务： "+numStrings+"计算结果为： "+sum);
        countDownLatch.countDown();
    }

    public void sum(){
        System.out.println("汇总线程 " + Thread.currentThread().getName() + " 开始执行！");
        int sum = 0;
        for (int num : nums){
            sum += num;
        }
        System.out.println("Sum is "+ sum);
    }

    public static void main(String[] args) throws IOException {
        List<String> contents = readFile();
        CountDownLatch countDownLatch = new CountDownLatch(contents.size());
        MyCountDownLatch spinLockSum = new MyCountDownLatch(contents.size());
        for (int i = 0; i < contents.size(); i++){
            final int j = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    spinLockSum.calc(contents.get(j),j,countDownLatch);
                }
            }).start();
        }
//        while(Thread.activeCount() > 2){  //忙等，消耗cpu资源
//        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        spinLockSum.sum();
    }

    public static List<String> readFile() throws IOException {
        List<String> contents = new ArrayList<>();
        String line = null;
        BufferedReader bufferedReader = new BufferedReader(new FileReader("/Users/chenliang/Documents/GitHub/MultiThread/src/CountDownLatch/numbers"));
        while ((line = bufferedReader.readLine()) != null){
            contents.add(line);
        }
        bufferedReader.close();

        return contents;
    }


}
