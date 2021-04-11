import org.openjdk.jol.info.ClassLayout;

public class C {
    static C c1 = new C();
    public static void main(String[] args) throws InterruptedException {
        // 无锁 unlock
        System.out.println(ClassLayout.parseInstance(c1).toPrintable());
//        Thread.sleep(3000);
//        C c  = new C();
//        System.out.println(ClassLayout.parseInstance(c).toPrintable());
        //
//        synchronized (c){
//
//            System.out.println(ClassLayout.parseInstance(c).toPrintable());
//        }
        System.out.println(ClassLayout.parseInstance(c1).toPrintable());
//        System.out.println(c.hashCode());
//        System.out.println(Integer.toHexString(c.hashCode()));
//        System.out.println(Integer.toHexString(536920325));
//        System.out.println(ClassLayout.parseInstance(c).toPrintable());
//
//        C[] d = new C[63];
//        System.out.println(ClassLayout.parseInstance(d).toPrintable());

    }
}
