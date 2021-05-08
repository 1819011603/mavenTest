package Thread.AQS;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

// 同时能有两个线程获取锁

//	利用AQS实现自定义锁，只需要自定义实现一个同步器，重写AQS中两个方法tryAcquire和tryRelease，如果是
//共享锁的话则只需重写tryAcquireShared和tryReleaseShared两个方法，如果需要实现Condition效果则需要另外
//重写isHeldExclusively方法。

public class TwinLock implements Lock {
    private static Sync sync = null;
    public TwinLock(){
        sync = new FairSync(2);
    }
    public TwinLock(boolean f){
        if(f){
            sync = new noFairSync(2);

        }else sync = new FairSync(2);
    }
    public int getState(){
        return sync.getS();
    }
    public int getLen(){
        return sync.getQueueLength();
    }

    public static class FairSync extends Sync{

        FairSync(int num) {
            super(num);
        }

        @Override
        protected int tryAcquireShared(int arg) {
            for (; ;){
                int c = getState();
                int newState = c-arg;
                if (newState < 0)return -1;
                // 公平锁
                if(!hasQueuedPredecessors() && compareAndSetState(c,newState)){
                    return newState;
                }
                return -1;
            }
        }
    }
    public static class noFairSync extends Sync{

        noFairSync(int num) {
            super(num);
        }
        @Override
        protected int tryAcquireShared(int arg) {
            // 非公平获取锁
            for (; ;){
                int c = getState();
                int newState = c-arg;
                if(newState < 0 ||  compareAndSetState(c,newState)){
                    return newState;
                }

            }
        }
    }
    public static class Sync extends AbstractQueuedSynchronizer{
        public int getS(){
            return getState();
        }
        Sync(int num){
            if (num<=0){
                throw new RuntimeException("锁资源数需要大于0");
            }
            setState(num);
        }
        @Override
        protected int tryAcquireShared(int arg) {
            /*一般的思想*/
            /*//获取此时state
            int currentState = getState();
            //获取剩余state
            int newState = currentState - arg;
            //如果剩余state小于0则直接返回负数
            //否则尝试更新state，更新成功就说明获取成功，返回大于等于0的数
            return newState < 0 ? newState : compareAndSetState(currentState, newState) ? newState : -1;*/

            /*更好的思想
             * 在上面的实现中，如果剩余state值大于0，那么只尝试CAS一次，如果失败就算没有获取到锁，此时该线程会进入同步队列
             * 在下面的实现中，如果剩余state值大于0，那么如果尝试CAS更新不成功，会在for循环中重试，直到剩余state值小于0或者更新成功
             *
             * 两种方法的不同之处在于，对CAS操作是否进行重试，这里建议第二种
             * 因为可能会有多个线程同时获取多把锁，但是由于CAS只能保证一次只有一个线程成功，因此其他线程必定失败
             * 但此时，实际上还是存在剩余的锁没有被获取完毕的，因此让其他线程重试，相比于直接加入到同步队列中，对于锁的利用率更高！
             *
             * */
            for (; ;){
                int c = getState();
                int newState = c-arg;
                if (newState < 0)return -1;
                // 公平锁
                if(!hasQueuedPredecessors() && compareAndSetState(c,newState)){
                    return newState;
                }
                return -1;
            }

            // 非公平获取锁
//            for (; ;){
//                int c = getState();
//                int newState = c-arg;
//                if(newState < 0 ||  compareAndSetState(c,newState)){
//                    return newState;
//                }
//
//            }

            // 效率比较低 因为park很耗时间的
//            int c = getState();
//            int newState = c-arg;
//            if(newState < 0 || compareAndSetState(c,newState)){
//                return newState;
//            }
//            return -1;

        }

        @Override
        protected boolean tryReleaseShared(int arg) {
            for (; ;){
                int c = getState();
                int newState = c+arg;
                if(compareAndSetState(c,newState)){
                    return true;
                }
            }
        }

        @Override
        protected boolean isHeldExclusively() {
            return false;
        }
    }

    @Override
    public void lock() {
        sync.acquireShared(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireSharedInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquireShared(1)>=0;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireSharedNanos(1,unit.toNanos(time));
    }

    @Override
    public void unlock() {
    sync.releaseShared(1);
    }

    @Override
    public Condition newCondition() {
       throw new UnsupportedOperationException();
    }
}
