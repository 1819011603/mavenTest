package Thread;

public class ShutDown {
    public static void main(String[] args) throws InterruptedException {
        Runner one = new Runner();
        Thread currentThread = new Thread(one,"one");
        currentThread.start();
        Thread.sleep(1000);
        currentThread.interrupt();

        Runner two = new Runner();
        currentThread = new Thread(two);
        currentThread.start();
        Thread.sleep(1000);
        two.cancer();
        System.out.println(one.isOn());
        System.out.println(two.isOn());
    }
    private static class Runner implements  Runnable{
        long i;
        private volatile boolean on  = true;
        @Override
        public void run() {
            while (on && !Thread.currentThread().isInterrupted()){
                i++;
            }
            System.out.println("Count: " + i);
        }
        public void cancer(){
            on = false;
        }

        public boolean isOn() {
            return on;
        }
    }
}
