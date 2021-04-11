package Thread;

public class Join {
    public static void main(String[] args) throws InterruptedException {
        Thread previous = Thread.currentThread();
        for(int i = 0; i < 10; i++){
            Thread thread = new Thread(new Runner(previous),String.valueOf(i));
            thread.start();
            previous = thread;
        }
        Thread.sleep(100);
        System.out.println("main end");

    }
    private static class Runner implements Runnable{
        Thread thread;
        public Runner(Thread thread){
            this.thread = thread;
        }
        @Override
        public void run() {

            try {
                if(!thread.getName().equals("main"))
                thread.join();
            } catch (InterruptedException e) {

            }
            System.out.println(Thread.currentThread().getName() + "...");

        }
    }
}
