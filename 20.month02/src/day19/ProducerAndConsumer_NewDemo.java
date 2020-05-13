package day19;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: java.shangguigu.learn
 * @description: 新版本的生产者消费者模式
 * @author: Hao Peng
 * @create: 2020-02-19 19:44
 **/
public class ProducerAndConsumer_NewDemo {
    public static void main(String[] args) {
        MyResource myResource = new MyResource(new ArrayBlockingQueue<>(10));
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t 生产线程启动");
            try {
                myResource.myProduce();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"prod").start();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t 消费线程启动");
            try {
                myResource.myConsum();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"consum").start();
        try {TimeUnit.SECONDS.sleep(5);} catch (InterruptedException e) {e.printStackTrace();}
        System.out.println("5秒钟时间到，停");
        myResource.stop();
    }
}

class MyResource {
    //高并发变成都需要保证可见性，因此需要加volatile，生产消费之间可见
    private volatile boolean FLAG = true;
    //多线程下禁止使用number++，很容易埋雷，需要使用原子整型确保安全
    private AtomicInteger atomicInteger = new AtomicInteger();

    //高手编程都不是传具体类，而是传接口，写架构要保证通用性
    BlockingQueue<String> blockingQueue = null;
    //写，要往高处写，尽量写抽象；查要足够具体，往细节落地，要有日志排查
    public MyResource(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
        System.out.println(blockingQueue.getClass().getName());
    }

    //写成产者代码
    public void myProduce() throws Exception {
        //相关临时变量需要在for/while循环之外定义，避免引用过多
        String data = null;
        boolean retvalue;
        while (FLAG){//避免虚假唤醒
            data = atomicInteger.getAndIncrement() + "";
            retvalue = blockingQueue.offer(data,2L, TimeUnit.SECONDS);
            if (retvalue){
                System.out.println(Thread.currentThread().getName() + "\t插入队列" + data + "成功");
            } else {
                System.out.println(Thread.currentThread().getName() + "\t插入队列" + data + "失败");
            }
            try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}
        }
        System.out.println(Thread.currentThread().getName() + "\t大老板叫停了，生产结束");
    }
    //写消费者代码
    public void myConsum() throws Exception {

        String result = null;
        while (FLAG){//避免虚假唤醒
            result = blockingQueue.poll(2L, TimeUnit.SECONDS);
            if (null == result || result.equalsIgnoreCase("")){
                FLAG = false;
                System.out.println(Thread.currentThread().getName() + "\t超过2秒钟没有渠道蛋糕，消费退出");
                System.out.println();
                System.out.println();
                return;
            }
            System.out.println(Thread.currentThread().getName() + "\t消费队列蛋糕" + result + "成功");
        }
    }
    public void stop(){
        FLAG = false;
    }
}
