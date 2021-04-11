package Thread.Design.Singleton;

// 双重检查锁定
// 添加volatile关键字之后的双重检查锁模式是一种比较好的单例实现模式，能够保证在多线程的情况下线程安全也不会有性能问题。
// 但存在反序列化问题  序列化后的读取对象是 新new的
public class DoubleCheckLock {
    private static volatile DoubleCheckLock doubleCheckLock;
    private DoubleCheckLock(){}

    /*
        双层校验, 第一次校验不是线程安全的，也就是说可能有多个线程同时得到singleton为null的结果，
        接下来的同步代码块保证了同一时间只有一个线程进入，而第一个进入的线程会创建对象，
        等其他线程再进入时对象已创建就不会继续创建。这是一个很巧妙的方式，如果对整个方法同步，
        所有获取单例的线程都要排队，但实际上只需要对创建过程同步来保证"单例"，
        多个线程不管是否已经有单例可以同时去请求。
    */
    public static DoubleCheckLock newInstance(){
        if(doubleCheckLock == null){
            synchronized(DoubleCheckLock.class){
                if(doubleCheckLock == null){
                    doubleCheckLock = new DoubleCheckLock();

                }
            }
        }
        return doubleCheckLock;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(()->
                    System.out.println(DoubleCheckLock.newInstance().hashCode())
                    ).start();

        }
    }
}
