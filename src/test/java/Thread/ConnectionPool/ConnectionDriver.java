package Thread.ConnectionPool;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;

public class ConnectionDriver {
    static class ConnectionHandler implements InvocationHandler{
        // 处理的事情就是休眠
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if(method.getName().equals("commit")){
                Thread.sleep(100);
            }
            return null;
        }
    }
    // 动态代理构建一个Connection对象  该Connection的代理实现  仅是在commit() 方法时休眠100millis
    public static final Connection createConnection(){
        return (Connection) Proxy.newProxyInstance(ConnectionDriver.class.getClassLoader(),new Class<?>[]{Connection.class},new ConnectionHandler());
    }
}
