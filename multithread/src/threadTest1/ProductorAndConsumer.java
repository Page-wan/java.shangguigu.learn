package threadTest1;

/**
 * @program: java.shangguigu.learn
 * @description: 生产者消费者模型演示
 * @author: Hao Peng
 * @create: 2019-12-16 19:20
 **/


class Clerk {
    private int productCount = 0;
    public synchronized void produceProduct() {
        if (productCount < 20){
            productCount++;
            System.out.println(Thread.currentThread().getName() + "：开始生产第" + productCount + "个产品");
            notify();
        } else {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public synchronized void consumProduct() {
        if (productCount > 0){
            System.out.println(Thread.currentThread().getName() + "：开始消费第" + productCount + "个产品");
            productCount--;
            notify();
        } else {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
class Producer extends Thread {
    private Clerk clerk;
    public Producer(Clerk clerk){
        this.clerk = clerk;
    }
    @Override
    public void run() {
        System.out.println(getName() + ":开始生产产品");
        while (true){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clerk.produceProduct();
        }
    }
}
class Consumer extends Thread {
    private Clerk clerk;
    public Consumer(Clerk clerk){
        this.clerk = clerk;
    }
    @Override
    public void run() {
        System.out.println(getName() + ":开始消费产品");
        while (true){
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clerk.consumProduct();
        }
    }
}
public class ProductorAndConsumer {
    public static void main(String[] args) {
        Clerk clerk = new Clerk();
        Producer p1 = new Producer(clerk);
        Consumer c1 = new Consumer(clerk);
        p1.setName("生产者1");
        c1.setName("消费者1");
        p1.start();
        c1.start();
    }
}
