package day19;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: java.shangguigu.learn
 * @description: 生产者消费者传统版本
 * @author: Hao Peng
 * @create: 2020-02-19 19:27
 **/
public class ProducerAndConsumer_TraditionalDemo {

    public static void main(String[] args) {
        ShareData shareData = new ShareData();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                shareData.increment();
            }
        },"aaa").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                shareData.decrement();
            }
        },"bbb").start();
    }
}

class ShareData {
    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment() {
        lock.lock();
        try{
            //判断是否能执行生产
            while ( number != 0) {
                //等待，不能执行生产，一直死循环
                condition.await();
            }
            //跳出循环表示可以执行生产
            number++;
            System.out.println(Thread.currentThread().getName() + "线程\t执行生产操作，当前值变为：" + number);
            //通知唤醒
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void decrement() {
        lock.lock();
        try{
            //判断是否能执行消费
            while ( number == 0) {
                //等待，不能执行消费，一直死循环
                condition.await();
            }
            //跳出循环表示可以执行消费
            number--;
            System.out.println(Thread.currentThread().getName() + "线程\t执行消费操作，当前值变为：" + number);
            //通知唤醒
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
