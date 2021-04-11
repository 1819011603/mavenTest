package Thread.Design.Singleton;

// 线程安全严重
public class Lazy01 {
    private static Lazy01 lazy01;
    // 防止使用者使用构造器初始化对象
    private Lazy01(){}
    public static Lazy01 newInstance() throws InterruptedException {
        if(lazy01 == null){
            Thread.sleep(10);
            lazy01 = new Lazy01();
        }
        return lazy01;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                try {
                    System.out.println(Lazy01.newInstance().hashCode());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
