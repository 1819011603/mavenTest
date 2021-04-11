package Thread.Design.Singleton;

// 内部类  延迟初始化
public class DelayInitialization {

    private DelayInitialization(){}
    static class InstanceHolder{

        public static DelayInitialization delayInitialization = new DelayInitialization();

    }
    public static DelayInitialization newInstance(){
        return InstanceHolder.delayInitialization;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                System.out.println(DelayInitialization.newInstance().hashCode());
            }).start();
        }
    }
}
