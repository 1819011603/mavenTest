package Thread;

public class Daemon {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new DaemonRunner(),"Daemon");
        thread.setDaemon(true);
        thread.start();
//        Thread.sleep(100);
//        Thread.sleep(99);
    }
    static class DaemonRunner implements Runnable{
        @Override
        public void run() {
            try {
                System.out.println("daemonThread is start");
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("daemonThread is end");
            }
        }
    }
}
