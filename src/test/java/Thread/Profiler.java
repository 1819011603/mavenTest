package Thread;

// 分析器
public class Profiler {
    private final static ThreadLocal<Long> threadLocal = new ThreadLocal<Long>(){
        @Override
        protected Long initialValue() {
            return System.currentTimeMillis();
        }
    };
    public static final void begin(){
        threadLocal.set(System.currentTimeMillis());
    }
    public static final long end(){
        return System.currentTimeMillis()-threadLocal.get();
    }

    public static void main(String[] args) throws InterruptedException {
        Profiler.begin();
        Thread.sleep(11);
        System.out.println(Profiler.end());
    }
}
