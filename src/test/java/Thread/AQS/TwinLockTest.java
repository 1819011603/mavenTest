package Thread.AQS;

import sun.security.krb5.internal.TGSRep;

import java.util.Arrays;
import java.util.concurrent.locks.Lock;

public class TwinLockTest {
    static int[] ints;
    static int[] unints;
    public void FairTest() throws InterruptedException {
        ints = new int[10];
        TwinLock lock = new TwinLock();

        class Worker extends Thread{
            @Override
            public void run() {


                while (true){

//                    System.out.println(lock.getLen());

                    lock.lock();
                    ints[Thread.currentThread().getName().charAt(Thread.currentThread().getName().length()-1)-'0']++;
                    try {

                        System.out.println(Thread.currentThread().getName()+ " lock" + ",lock.getState(): " + lock.getState() + ",queueLength: " + lock.getLen());


                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            lock.unlock();

                        System.out.println(Thread.currentThread().getName()+ "      unlock" + ",lock.getState(): " +lock.getState()+ ",queueLength: " + lock.getLen());

                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        }


                }
            }
        }
        for (int i = 0; i < 10; i++) {
            Worker w = new Worker();
            w.setDaemon(true);// 只有Daemon线程会结束的
            w.start();
            Thread.sleep(10);
        }
        for (int i = 0; i < 10; i++) {
            Thread.sleep(200);
            System.out.println();

        }
        System.out.println(Arrays.toString(ints));
    }
    public void unFairTest() throws InterruptedException {
        TwinLock lock = new TwinLock(true);
        unints = new int[10];
        class Worker extends Thread{
            @Override
            public void run() {


                while (true){

//                    System.out.println(lock.getLen());
                    lock.lock();
                    unints[Thread.currentThread().getName().charAt(Thread.currentThread().getName().length()-1)-'0']++;
                    try {

                        System.out.println(Thread.currentThread().getName()+ " lock" + ",lock.getState(): " + lock.getState() + ",queueLength: " + lock.getLen());

                        Thread.sleep(1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();

                        System.out.println(Thread.currentThread().getName()+ "      unlock" + ",lock.getState(): " +lock.getState()+ ",queueLength: " + lock.getLen());

//                        System.out.println(Arrays.toString(unints));
                    }


                }
            }
        }
        for (int i = 0; i < 10; i++) {
            Worker w = new Worker();
            w.setDaemon(true);// 只有Daemon线程会结束的
            w.start();

        }
        for (int i = 0; i < 10; i++) {
            Thread.sleep(20);
            System.out.println();

        }


    }
    public static void main(String[] args) throws InterruptedException {
        /*lock测试，或许总会出现固定线程获取锁，因为AQS默认是实现是非公平锁*/
        new TwinLockTest().FairTest();

        System.out.println();
        System.out.println();

        new TwinLockTest().unFairTest();
        Thread.sleep(100);
        System.out.println(Arrays.toString(ints));
        System.out.println(Arrays.toString(unints));

    }
}
