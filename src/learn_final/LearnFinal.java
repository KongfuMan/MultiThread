package learn_final;

//写final域的重排序规则
public class LearnFinal {
    private int a;
    private final int b;

    public LearnFinal() {
        this.b = 1;
    }

    //代码块比构造方法先执行
//    {
//        this.b = 2;
//    }

    private LearnFinal l;

    public void write(){
        l = new LearnFinal();    //call constructor to trigger write to final b
    }

    public void read(){
        int temp1 = l.b;
        int temp2 = l.a;
    }
}
