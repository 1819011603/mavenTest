package Thread.Design.Factory.AbstractFactory;

public class LowEndFactory implements AbstractFactory{
    @Override
    public IMask createMask() {
        IMask mask = new LowEndMask();
        // LowEndMask 100 行初始化代码
        return mask;
    }

    @Override
    public IProtectiveSuit createSuit() {
        IProtectiveSuit suit =  new LowEndProtectiveSuit();
        // .....
        //  LowEndProtectiveSuit的100行初始化代码
        return suit;
    }
}
