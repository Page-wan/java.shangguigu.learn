package sealticket;

/**
 * @program: java.shangguigu.learn
 * @description: 模拟买票机制
 * @author: Hao Peng
 * @create: 2019-12-10 22:02
 **/
public class SealWindow {
    public static void main(String[] args) {
        SealThread sw = new SealThread();
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

class SealThread implements Runnable{
    private int ticket = 100;
    Object obj = new Object();
    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            show();
            if (ticket == 0) break;
        }
    }
    private synchronized void show(){
        if (ticket > 0) {
            System.out.println(Thread.currentThread().getName() + ":" + ticket);
            ticket--;
        }
    }
}
