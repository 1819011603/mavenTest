package Thread.Design.Factory;

// 定义：定义一个工厂类，他可以根据参数的不同返回不同类的实例，被创建的实例通常都具有共同的父类
/*
在简单工厂模式中用于被创建实例的方法通常为静态(static)方法,因此简单工厂模式又被成为静态工厂方法(Static Factory Method)
需要什么，只需要传入一个正确的参数，就可以获取所需要的对象，而无需知道其实现过程

　      （1）工厂类集中了所有产品的创建逻辑，职责过重，一旦异常，整个系统将受影响
　　　　（2）使用简单工厂模式会增加系统中类的个数(引入新的工厂类)，增加系统的复杂度和理解难度
　　　　（3）系统扩展困难，一旦增加新产品不得不修改工厂逻辑，在产品类型较多时，可能造成逻辑过于复杂
　　　　（4）简单工厂模式使用了static工厂方法，造成工厂角色无法形成基于继承的等级结构。


        简单工厂模式的适用环境
　　　　(1)工厂类负责创建对的对象比较少，因为不会造成工厂方法中的业务逻辑过于复杂

　　　　(2)客户端只知道传入工厂类的参数，对如何创建对象不关心

* */
public class SimpleFactory {
    // 简单工厂模式 if-else  不封闭  因为每增加一个产品类，需要在工厂代码里面增加if-else判断。
    public static Vehicle getVehicle(String arg){
        Vehicle vehicle = null;
        if("Car".equalsIgnoreCase(arg)){
            vehicle = new Car();
        }else if("Train".equalsIgnoreCase(arg)){
            vehicle = new Train();
        }else if("Plane".equalsIgnoreCase(arg)){
            vehicle = new Plane();
        }
        return vehicle;
    }

    public static void main(String[] args) {
        Vehicle v;
        v = SimpleFactory.getVehicle("car");
        v.go();
        v = SimpleFactory.getVehicle("train");
        v.go();
        v = SimpleFactory.getVehicle("plane");
        v.go();
    }
}
