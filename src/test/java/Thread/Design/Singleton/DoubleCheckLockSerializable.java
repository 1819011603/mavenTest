package Thread.Design.Singleton;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

// 反射仍然可以创建新实例
public class DoubleCheckLockSerializable implements Serializable {
    // 实现了Serializable接口的 类要指定serialVersionUID

    private static final long serialVersionUID = -6032874525815389228L;
    private  static DoubleCheckLockSerializable doubleCheckLockSerializable;
    private DoubleCheckLockSerializable() {


    }
    public static DoubleCheckLockSerializable newInstance() {
        if(doubleCheckLockSerializable == null){
            synchronized (DoubleCheckLockSerializable.class){
                if(doubleCheckLockSerializable == null)
                    doubleCheckLockSerializable = new DoubleCheckLockSerializable();
            }
        }
        return doubleCheckLockSerializable;
    }

    // 不加这个方法  readResolve就new一个新的对象
    private Object readResolve(){
        return doubleCheckLockSerializable;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
//        DoubleCheckLockSerializable.newInstance();
        Class<DoubleCheckLockSerializable> c = DoubleCheckLockSerializable.class;
        Constructor<DoubleCheckLockSerializable> constructor = c.getDeclaredConstructor();
        constructor.setAccessible(true);
        DoubleCheckLockSerializable doubleCheckLockSerializable1 = constructor.newInstance();
        System.out.println(doubleCheckLockSerializable1 == DoubleCheckLockSerializable.newInstance());

        String path = DoubleCheckLockSerializable.class.getResource("/").getPath();
        System.out.println(path);
        path = DoubleCheckLockSerializable.class.getResource("").getPath();
        System.out.println(path);
        String file =  path + DoubleCheckLockSerializable.class.getSimpleName() + ".txt";
        System.out.println(file);
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file));
        outputStream.writeObject(DoubleCheckLockSerializable.newInstance());
        ObjectInputStream in  = new ObjectInputStream(new FileInputStream(file));
        DoubleCheckLockSerializable checkLock = (DoubleCheckLockSerializable) in.readObject();
        //  不写readResolve方法 将会返回false
        System.out.println(checkLock == DoubleCheckLockSerializable.newInstance());
    }

}
