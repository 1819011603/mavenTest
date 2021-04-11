package Thread;

public class WaitNotify {
    static boolean flag = true;
    static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException{
        Thread waitThread = new Thread(new Wait());
        waitThread.start();
        Thread.sleep(1000);
        Thread notifyThread = new Thread(new Notify());
        notifyThread.start();

    }
    private static class Wait implements Runnable{
        @Override
        public void run() {
            synchronized(lock){
                while (flag){
                    try {
                        System.out.println( "Wait in while: " + System.currentTimeMillis());
                        lock.wait();
                    } catch (InterruptedException e) {

                    }
                }
                System.out.println( "Wait out while: " + System.currentTimeMillis());
            }
        }
    }
    private static class Notify implements Runnable{

        @Override
        public void run() {
            synchronized(lock){
                System.out.println("Notify in first: "+System.currentTimeMillis());
                lock.notifyAll();
                flag = false;
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {

                }
            }
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//
//            }
            synchronized (lock){
                System.out.println("Notify in second: " + System.currentTimeMillis());
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {

                }
            }
        }
    }
}
