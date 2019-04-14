package t7_Singleton;

// 多个线程在调用getInstance方法时，不会存在线程安全问题
// 因为是都是读取共享资源，没有写操作
public class SingletonEager {
    private SingletonEager() {
    }

    private static SingletonEager instance = new SingletonEager();

    public static SingletonEager getInstance(){
        return instance;
    }

    public static void main(String[] args) {
        SingletonEager s1 = SingletonEager.getInstance();
        SingletonEager s2 = SingletonEager.getInstance();
        SingletonEager s3 = SingletonEager.getInstance();
        SingletonEager s4 = SingletonEager.getInstance();

        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);
        System.out.println(s4);
    }
}
