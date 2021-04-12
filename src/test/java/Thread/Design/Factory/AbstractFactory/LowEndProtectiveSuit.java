package Thread.Design.Factory.AbstractFactory;

public class LowEndProtectiveSuit implements IProtectiveSuit {
    @Override
    public void showSuit() {
        System.out.println("我是低端防护服");
    }
}
