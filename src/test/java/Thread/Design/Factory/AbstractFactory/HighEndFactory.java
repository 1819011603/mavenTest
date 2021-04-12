package Thread.Design.Factory.AbstractFactory;

public class HighEndFactory implements AbstractFactory{
    @Override
    public IMask createMask() {
        IMask mask =  new HighEndMask();
        // .....
        // HighEndMask的100行初始化代码
        return mask;
    }

    @Override
    public IProtectiveSuit createSuit() {
        IProtectiveSuit suit =  new HighEndProtectiveSuit();
        // .....
        //  HighEndProtectiveSuit的100行初始化代码
        return suit;
    }
}
