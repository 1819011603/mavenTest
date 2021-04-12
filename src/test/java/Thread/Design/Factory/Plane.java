package Thread.Design.Factory;

public class Plane implements Vehicle{
    @Override
    public void go() {
        System.out.println("this is a Plane");
    }
}
