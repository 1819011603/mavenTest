package Thread.Design.Singleton;

// 第一个枚举单例，你要先理解enum， 在你的代码中INSTANCE本身就是一个EnumSingleton类型的引用, 与普通的饿汉式单例相似。


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/*
        了解JVM的类加载机制的朋友应该对这部分比较清楚。static类型的属性会在类被加载之后被初始化，我们在深度分析Java的ClassLoader机制（源码级别）中介绍过，
        当一个Java类第一次被真正使用到的时候静态资源被初始化、Java类的加载和初始化过程都是线程安全的（因为虚拟机在加载枚举的类的时候，
        会使用ClassLoader的loadClass方法，而这个方法使用同步代码块保证了线程安全）。所以，创建一个enum类型是线程安全的。

        也就是说，我们定义的一个枚举，在第一次被真正用到的时候，会被虚拟机加载并初始化，而这个初始化过程是线程安全的。
        而我们知道，解决单例的并发问题，主要解决的就是初始化过程中的线程安全问题。

        所以，由于枚举的以上特性，枚举实现的单例是天生线程安全的。

        枚举可避免反序列化破坏单例前面我们提到过，使用“双重校验锁”实现的单例其实是存在一定问题的，
        就是这种单例有可能被序列化锁破坏，关于这种破坏及解决办法，参看单例与序列化的那些事儿，这里不做更加详细的说明了。
        */


// 不能容忍 反射仍然可以创建新对象 即便在不同包中的类中使用反射  现在不明白为什么这是推荐写法 我建议使用第二种枚举单例
public class EnumSingleton01 {
    private EnumSingleton01(){

    }
    private enum Singleton{
        INSTANCE;
        private   EnumSingleton01 instance;
        // jvm 保证只调用一次
        Singleton(){
            instance = new EnumSingleton01();

        }
        private EnumSingleton01 getInstance(){
            return instance;
        }
    }
    public static EnumSingleton01 newInstance(){
        return Singleton.INSTANCE.getInstance();
    }

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        // 不能容忍 反射仍然可以创建新对象 即便在不同包中的类中使用反射  现在不明白为什么这是推荐写法 我建议使用第二种枚举单例
        Class<EnumSingleton01> c = EnumSingleton01.class;
        Constructor<EnumSingleton01> c1 = c.getDeclaredConstructor();
        c1.setAccessible(true);
        EnumSingleton01 enumSingleton =  c1.newInstance();
        System.out.println(enumSingleton.hashCode());
        System.out.println(EnumSingleton01.newInstance().hashCode());
        System.out.println(enumSingleton == EnumSingleton01.newInstance());
    }

}

/*
解释：
    Java中的枚举和其它语言不同，它是一个对象。早期的 Java 是没有枚举类型的大家都用类似于单例的方式来实现枚举，
    简单的说就是让构造 private 化，在 static 块中产生多个 final 的对象实例，通过比较引用（或 equals）来进行比较，
    这种模式跟单例模式相似，不能由使用者产生实例，但是有多个实例——可惜，我不知道这种模式叫什么名字，有知道麻烦告知一声。
*/

// 早期用类的方式实现的枚举 饿汉方式
class MyEnum {
    public static MyEnum NumberZero;
    public static MyEnum NumberOne;
    public static MyEnum NumberTwo;
    public static MyEnum NumberThree;

    static {
        NumberZero = new MyEnum(0);
        NumberOne = new MyEnum(1);
        NumberTwo = new MyEnum(2);
        NumberThree = new MyEnum(3);
    }

    private final int value;

    private MyEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}


/*
    从 Java 5 开始有枚举类型之后，类似的实现可以这样 类似类方式实现的枚举实现
    回头说说枚举变单例
        上面说了，早期就是通过类似单例模式的方式来实现的枚举。
        而后的枚举实例也和类方式实现极为相似，那么，如果枚举值只有 1 个的时候，其实例也就只有 1 个，就完全符合了单例模式的限定。
        由于 Java 的 enum 可以跟类一样任意定义方法和属性，所以就完全可以用枚举来实现单例。这是其它语言如 C++、C# 做不到的。
*/
enum MyEnum1 {
    NumberZero(0),
    NumberOne(1),
    NumberTwo(2),
    NumberThree(3);

    private final int value;

    MyEnum1(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}