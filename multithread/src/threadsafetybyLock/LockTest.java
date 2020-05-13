package threadsafetybyLock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: java.shangguigu.learn
 * @description: 用Lock实现类解决线程安全问题
 * @author: Hao Peng
 * @create: 2019-12-15 20:23
 **/
public class LockTest {
    public static void main(String[] args) {
        SealThread2 sw = new SealThread2();
        Thread w1 = new Thread(sw);
        Thread w2 = new Thread(sw);
        Thread w3 = new Thread(sw);
        w1.setName("窗口1");
        w2.setName("窗口2");
        w3.setName("窗口3");
        w1.start();
        w2.start();
        w3.start();
    }
}

class SealThread2 implements Runnable{
    private int ticket = 100;
    private ReentrantLock lock = new ReentrantLock();
    @Override
    public void run() {
        while (true){
            try {
                lock.lock();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (ticket > 0) {
                    System.out.println(Thread.currentThread().getName() + ":" + ticket);
                    ticket--;
                } else {
                    break;
                }
            }finally {
                lock.unlock();
            }
        }
    }
}