package Thread.Design.Factory;

public class Train implements Vehicle{
    @Override
    public void go() {
        System.out.println("this is a Train");
    }
}
