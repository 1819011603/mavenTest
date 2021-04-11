import org.openjdk.jol.info.ClassLayout;

public class A {

    boolean f;
    int t;
    class B{
        int a;
        int c;
    }
     B b = new B();
     B b1 = new B();
    public static void main(String[] args) {
        A a = new A();

        System.out.println(ClassLayout.parseInstance(a));
        System.out.println(ClassLayout.parseInstance(a).toPrintable());
        System.out.println(a.hashCode());
        System.out.println(Integer.toHexString(a.hashCode()));
        System.out.println(ClassLayout.parseInstance(a).toPrintable());
        System.out.println(ClassLayout.parseInstance(a.b).toPrintable());

        A[] c = new A[255];
        System.out.println(ClassLayout.parseInstance(c).toPrintable());
    }
}
