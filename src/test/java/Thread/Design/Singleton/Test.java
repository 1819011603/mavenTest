package Thread.Design.Singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Test {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<EnumSingleton01> c = EnumSingleton01.class;
        Constructor<EnumSingleton01> c1 = c.getDeclaredConstructor();
        c1.setAccessible(true);
        EnumSingleton01 enumSingleton = c1.newInstance();
        System.out.println(enumSingleton.hashCode());
        System.out.println(EnumSingleton01.newInstance().hashCode());
        System.out.println(enumSingleton == EnumSingleton01.newInstance());
    }
}
