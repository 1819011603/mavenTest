package Thread.Design.Singleton;

import java.io.*;

public class DoubleCheckLockSerializable implements Serializable {
    private  static DoubleCheckLockSerializable doubleCheckLockSerializable;
    private DoubleCheckLockSerializable(){}
    public static DoubleCheckLockSerializable newInstance(){
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

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String path = DoubleCheckLockSerializable.class.getResource("/").getPath();
        System.out.println(path);
        path = DoubleCheckLockSerializable.class.getResource("").getPath();
        System.out.println(path);
        String file =  path + DoubleCheckLockSerializable.class.getSimpleName() + ".txt";
        System.out.println(file);
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file));
        outputStream.writeObject(DoubleCheckLockSerializable.newInstance());
        ObjectInputStream in  = new ObjectInputStream(new FileInputStream(new File(file)));
        DoubleCheckLockSerializable checkLock = (DoubleCheckLockSerializable) in.readObject();
        //  不写readResolve方法 将会返回false
        System.out.println(checkLock == DoubleCheckLockSerializable.newInstance());
    }

}
