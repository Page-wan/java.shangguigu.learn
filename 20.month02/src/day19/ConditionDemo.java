package day19;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: java.shangguigu.learn
 * @description: Condition测试精确唤醒
 * @author: Hao Peng
 * @create: 2020-02-19 20:16
 **/
public class ConditionDemo {
    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();

        new Thread(() -> {
            shareResource.printFun(1);
        },"1").start();

        new Thread(() -> {
            shareResource.printFun(2);
        },"2").start();

        new Thread(() -> {
            shareResource.printFun(3);
        },"3").start();
    }
}

class ShareResource {
    private int number = 1;//1代表A线程执行，2代表B，3代表C
    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    public void printFun(int threadNumber){
        lock.lock();
        try{
            while (number != threadNumber){//1、判断
                if (threadNumber == 1){
                    c1.await();
                }else if (threadNumber == 2){
                    c2.await();
                } else if (threadNumber == 3){
                    c3.await();
                }
            }//2、干活
            for (int i = 1; i <= threadNumber * 5; i++) {
                System.out.println(Thread.currentThread().getName() + "打印：" + i);
            }//3、通知
            if (threadNumber == 1){
                number = 2;
                c2.signal();
            }else if (threadNumber == 2){
                number = 3;
                c3.signal();
            } else if (threadNumber == 3){
                number = 1;
                c1.signal();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}