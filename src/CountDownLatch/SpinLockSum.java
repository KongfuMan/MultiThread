package CountDownLatch;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SpinLockSum {
    private int[] nums; //保存当前行的所有数字

    public SpinLockSum(int lines){
        nums = new int[lines];
    }

    public void calc(String line, int index){
        String[] numStrings = line.split(",");
        int sum = 0;
        for (String s:numStrings){
            sum += Integer.parseInt(s);
        }

        nums[index] = sum;  //把当前行的和放到数组的指定位置
        System.out.println(Thread.currentThread().getName() + " 执行计算任务： "+numStrings+"计算结果为： "+sum);
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
        SpinLockSum spinLockSum = new SpinLockSum(contents.size());
        for (int i = 0; i < contents.size(); i++){
            final int j = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    spinLockSum.calc(contents.get(j),j);
                }
            }).start();
        }
        while(Thread.activeCount() > 2){
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
