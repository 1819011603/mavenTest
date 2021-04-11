package Thread.Design.Singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public enum EnumSingleton02 {
    // INSTANCE 就是这个单例  天生的单例 jvm保证
    INSTANCE(1),
    // INSTANCE INSTANCE2 双例
    INSTANCE2(2,"zzl");



    int val;
    String name;
    public static EnumSingleton02 newInstance(){
        return INSTANCE;
    }
    public int getVal(){
        return val;
    }

    public String getName() {
        return name;
    }

    EnumSingleton02(int val){
        this.val =val;
    }
    EnumSingleton02(int val,String name){
        this.val =val;
        this.name = name;
    }
    public void doSomething(){
        System.out.println("do Something!");
    }

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        EnumSingleton02 enumSingleton02 = EnumSingleton02.newInstance();
        enumSingleton02.doSomething();
        System.out.println(EnumSingleton02.INSTANCE2);
        System.out.println(EnumSingleton02.INSTANCE2.getName());
        System.out.println(enumSingleton02 == EnumSingleton02.INSTANCE2);
        // newInstance 直接报错
//        Class<EnumSingleton02> c = EnumSingleton02.class;
//        Constructor<EnumSingleton02> constructor = c.getDeclaredConstructor();
//        constructor.setAccessible(true);
//        constructor.newInstance();
//        System.out.println(c.newInstance());
    }

}
