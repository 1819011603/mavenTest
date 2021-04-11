package Thread.ConnectionPool;

import java.sql.Connection;
import java.util.LinkedList;

// 线程池
public class ConnectionPool {
    private final LinkedList<Connection> pool = new LinkedList<>();
    // 默认initialSize个线程放入线程池中
    public ConnectionPool(int initialSize){
        if(initialSize > 0){
            for(int i = 0; i < initialSize; i++){
                pool.addLast(ConnectionDriver.createConnection());

            }
        }

    }
    public LinkedList<Connection> getPool(){
        return pool;
    }

    // 释放一个Connection 将它加入的线程池中  末进
    public void releaseConnection(Connection connection)
    {
        if(connection!=null){
            synchronized (pool){
                pool.addLast(connection);
                pool.notifyAll();
            }
        }
    }
    // 等待millis后获得一个线程  从线程池中删除第一个  首出
    public Connection fetchConnection(long millis)throws InterruptedException{
        synchronized (pool){
            if(millis <= 0){
                // 等待  不满足加入等待队列
                while (pool.isEmpty()){
                    pool.wait();
                }
                return pool.removeFirst();
            }else {
                long future = System.currentTimeMillis() +millis;
                long remaining = millis;
                while (pool.isEmpty() && remaining> 0){
                    pool.wait(remaining);
                    remaining = future - System.currentTimeMillis();
                }
                Connection result = null;
                if(!pool.isEmpty()){
                    result = pool.removeFirst();
                }
                return result;
            }
        }
    }
}
