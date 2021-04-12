package Thread.Design.Factory;

public class Car implements Vehicle{
    @Override
    public void go() {
        System.out.println("this is a Car");
    }
}
