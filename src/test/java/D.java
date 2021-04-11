import org.openjdk.jol.info.ClassLayout;

public class D {
    static B b;
    public static void main(String[] args) throws InterruptedException {
        b = new B();
        System.out.println(ClassLayout.parseInstance(b).toPrintable());
        Thread t1 = new Thread(()->{
            synchronized (b){
                System.out.println(ClassLayout.parseInstance(b).toPrintable());
            }

        });
        t1.start();
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(()->{
            synchronized (b){
                int t = 3;
                while (t-- != 0){

                    System.out.println(ClassLayout.parseInstance(b).toPrintable());
                }
            }
        }).start();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(()->{
            synchronized (b){
                System.out.println(ClassLayout.parseInstance(b).toPrintable());
            }
        }).start();

        B b2 = new B();
        System.out.println(ClassLayout.parseInstance(b2).toPrintable());
//        long start = System.currentTimeMillis();
//        for (int i = 0; i < 100000000L; i++) {
//            b.parse();
//        }
//        long end = System.currentTimeMillis();
//        System.out.println(end - start);
    }
}
