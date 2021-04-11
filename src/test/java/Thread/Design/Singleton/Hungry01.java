package Thread.Design.Singleton;

// 饿汉模式  线程安全
// 缺点 类加载时就创建了实例  没有用的时候也会加载类 生成一个对象
public class Hungry01 {
    private static Hungry01 hungry = new Hungry01();
    private Hungry01(){}
    public static Hungry01 newInstance(){
        return Hungry01.hungry;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 50; i++) {
            new Thread(()->{
                System.out.println(Hungry01.newInstance().hashCode());
            }).start();

        }
    }
}
