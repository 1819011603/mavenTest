package Thread.Design.Singleton;

public class Lazy03 {

    private static Lazy03 lazy03;
    private Lazy03(){}
    public static Lazy03 newInstance(){
        if(lazy03 == null){
            synchronized(Lazy03.class){
                if(lazy03 == null){
                    // new 对象 在jvm分为3步 1.分配内存空间 2.初始化对象 3.将指针指向该对象  jvm为了效率和并发程度 会进行重排序
                    // 有可能会返回一个并未初始化的对象  导致出错
                    lazy03 = new Lazy03();
                }
            }
        }
        return lazy03;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                System.out.println(Lazy03.newInstance().hashCode());
            }).start();
        }
    }
}
