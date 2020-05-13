package volatileTest;

import java.util.concurrent.CyclicBarrier;

/**
 * @program: java.shangguigu.learn
 * @description: 不保证原子性测试
 * @author: Hao Peng
 * @create: 2020-03-10 20:22
 **/
public class ProblemAtomic {

    public static void main(String[] args) {
        //public CyclicBarrier(int parties, Runnable barrierAction)
        //这里第二个参数是要传入Runnable接口
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {
            System.out.println("********召唤神龙");
        });
        for (int i = 1; i <= 7; i++){
            new Thread(() -> {
                System.out.println("收集到第" + Thread.currentThread().getName() + "颗龙珠");
                try {
                    cyclicBarrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }
    }
}

class Resource {

    public volatile int data = 0;

    void addPlusPlus() {
        data++;
    }
}


class MySingleTon{
    private volatile MySingleTon mySingleTon;
    private MySingleTon(){}

    public MySingleTon getMySingleTon() {
        if (mySingleTon == null) {
            synchronized (MySingleTon.class) {
                if (mySingleTon == null) {
                    mySingleTon = new MySingleTon();
                }
            }
        }
        return mySingleTon;
    }
}