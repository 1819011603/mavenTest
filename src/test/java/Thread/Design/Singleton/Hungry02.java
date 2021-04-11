package Thread.Design.Singleton;

public class Hungry02 {
    private static Hungry02 hungry;
    static {
        hungry = new Hungry02();
    }
    private Hungry02(){}
    public static Hungry02 newInstance(){
        return Hungry02.hungry;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 50; i++) {
            new Thread(()->{
                System.out.println(Hungry02.newInstance().hashCode());
            }).start();

        }
    }
}
