package Thread;

import Thread.Design.Singleton.EnumSingleton01;
import Thread.Design.Singleton.EnumSingleton01;
import sun.nio.ch.ThreadPool;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.*;

public class Test {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, InterruptedException {
        ReadWriteLock r = new ReentrantReadWriteLock();
        Thread thread1 = new Thread(()->{



            System.out.println(Thread.currentThread().isInterrupted());
            System.out.println(Thread.currentThread().isInterrupted());
            LockSupport.park();//Thread.currentThread().isInterrupted()为true 无效
            LockSupport.park();//Thread.currentThread().isInterrupted()为true 无效
            LockSupport.park();//Thread.currentThread().isInterrupted()为true 无效
            for (;;){
                System.out.println("1");
                if(Thread.currentThread().isInterrupted()){
                    Thread.interrupted();//清除中断标志位false
                    LockSupport.park(); // 阻塞成功
                }
            }

        });
        thread1.start();
        thread1.interrupt();//


    }
}
