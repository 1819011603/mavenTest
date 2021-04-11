package Thread;

public class Interrupted {
    public static void main(String[] args) throws InterruptedException {
        Thread busyThread = new Thread(()->{
            System.out.println("没有发生InterruptException！");
            try {
                while (true){

                }
            }catch (Exception e){
                System.out.println("busy");
            }finally {
                System.out.println("busy finally");
            }


        });
        busyThread.setDaemon(true);
        Thread sleepThread = new Thread(()->{
            try{

                while (true){
                    Thread.sleep(1000);
                }
            }catch (InterruptedException e){
                System.out.println("发生InterruptException： " + e.getMessage());
            }finally {
                System.out.println("sleepThread!!!!");
            }
        });
        sleepThread.setDaemon(true);
        busyThread.start();
        sleepThread.start();

        Thread.sleep(5000);

        sleepThread.interrupt();
        busyThread.interrupt();

        System.out.println("sleepThread.interrupted:" + sleepThread.isInterrupted());
        System.out.println("busyThread.interrupted:" + busyThread.isInterrupted());
        Thread.sleep(104);
    }

}

