package Thread.Design.Singleton;

// 线程安全 加锁速度较慢
public class Lazy02 {
    private static Lazy02 lazy02;
    private Lazy02(){}
    public static Lazy02 newInstance(){
        synchronized (Lazy02.class){
            if(lazy02 == null){
                lazy02 = new Lazy02();
            }
            return lazy02;
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                System.out.println(Lazy02.newInstance().hashCode());
            }).start();
        }
    }
}
