package Thread.Design.Factory.AbstractFactory;

// 简单工厂模式有唯一的工厂类，工厂类的创建方法根据传入的参数做if-else条件判断，决定最终创建什么样的产品对象。 用户不需了解业务代码直接创建
// 一个工厂任务量太重 分几个工厂吧

// 工厂方法模式由多个工厂类实现工厂接口，利用多态来创建不同的产品对象，从而避免了冗长的if-else条件判断。
// 工厂类过多  我们就给他们分个组  再加一层抽象类

// 抽象工厂模式把产品子类进行分组，同组中的不同产品由同一个工厂子类的不同方法负责创建，从而减少了工厂子类的数量。


// 有很多种工厂 我统一将工厂其分成两组  低端工厂和高端工厂  低端和高端工厂都可以生产面罩和防护服（分组的思想 减少工厂个数）
// 工厂模式就是三层抽象  共同父类  产品  和 工厂的共同父类（单一方法）  和  生产该产品的工厂  ---- 导致工厂数目过多
// 抽象工厂就是四层抽象  共同父类  产品  、 产品所属的组的父类（多个方法） 和 每个组工厂（可以生产多种同组的产品）  生产的产品数 = 组工厂数 * 方法数
public interface AbstractFactory {
    IMask createMask();
    IProtectiveSuit createSuit();

     static void main(String[] args) {
        AbstractFactory factoryA = new LowEndFactory();
        AbstractFactory factoryB = new HighEndFactory();
        //创建低端口罩
        IMask maskA = factoryA.createMask();
        //创建高端口罩
        IMask maskB = factoryB.createMask();
        //创建低端防护服
        IProtectiveSuit suitA = factoryA.createSuit();
        //创建高端防护服
        IProtectiveSuit suitB = factoryB.createSuit();

        maskA.showMask();
        maskB.showMask();
        suitA.showSuit();
        suitB.showSuit();
    }
}
