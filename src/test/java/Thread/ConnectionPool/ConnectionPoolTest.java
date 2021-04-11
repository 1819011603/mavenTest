package Thread.ConnectionPool;

import java.sql.Connection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class ConnectionPoolTest {
    static ConnectionPool pool = new ConnectionPool(5);
    // Latch 占有   CountDownLatch是一个同步工具类，用来协调多个线程之间的同步，或者说起到线程之间的通信
    // CountDownLatch能够使一个线程在等待另外一些线程完成各自工作之后，再继续执行。使用一个计数器进行实现。计数器初始值为线程的数量。
    // 当每一个线程完成自己任务后，计数器的值就会减一。当计数器的值为0时，表示所有的线程都已经完成一些任务，
    // 然后在CountDownLatch上等待的线程就可以恢复执行接下来的任务。
    static CountDownLatch start = new CountDownLatch(1);
    static CountDownLatch end;

    public static void main(String[] args) throws InterruptedException {
        int threadCount = 10;
        // end等待20个线程
        end = new CountDownLatch(threadCount);
        int count = 50;
        AtomicInteger got = new AtomicInteger();
        AtomicInteger notGot = new AtomicInteger();
        //  创建20个线程
        for(int i = 0; i < threadCount; i++){
            Thread thread = new Thread(new ConnectionRunner(count,got,notGot),"ConnectionRunnerThread: " + i);
            thread.start();

        }
        // main执行完 start.countDown 去唤醒 start await的线程
        Thread.sleep(500);
        System.out.println("start.countDown");
        start.countDown();
        Thread.sleep(500);
        System.out.println("end await");
        end.await();
        System.out.println("等上面的线程执行完");

        System.out.println("total invoke: " + (threadCount * count));
        System.out.println("gotConnection: " + got);
        System.out.println("not got Connection:" + notGot);


    }
    static class ConnectionRunner implements Runnable{
        int count;
        AtomicInteger got;
        AtomicInteger notGot;
        public ConnectionRunner(int count,AtomicInteger got,AtomicInteger notGot){
            this.count = count;
            this.got = got;
            this.notGot = notGot;
        }

        @Override
        public void run() {
            try {


                // 等待 main线程 中的start.countDown调用
                System.out.println(Thread.currentThread().getName() + " not start");
                start.await();
                System.out.println(Thread.currentThread().getName() + " start");
            }catch (Exception e){

            }
            while(count > 0){
                try {
                    Connection connection = pool.fetchConnection(1000);
                    if(connection!=null){
                        try {
                            // 数据库处理 事务
                            connection.createStatement();
                            connection.commit();
                        }finally {
                            // 处理完毕 释放连接
                            pool.releaseConnection(connection);
                            got.incrementAndGet();
                        }
                    }else{
                        notGot.incrementAndGet();
                    }

                } catch (Exception e) {

                }finally {
                    count--;
                }
            }
            end.countDown();

        }
    }
}
