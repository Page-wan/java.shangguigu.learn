package dya18;

/**
 * @program: java.shangguigu.learn
 * @description: Semaphone测试
 * @author: Hao Peng
 * @create: 2020-02-18 22:24
 **/
public class SemaphoreDemo {
    public static void main(String[] args) {
        MyData myData = new MyData();
        for (int i = 1; i <= 20; i++){
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    myData.addPlusPlus();
                }
            },String.valueOf(i)).start();
        }
        //多线程常见的等待线程代码实现方式
        while (Thread.activeCount() > 2){
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName() + "最终获取值" + myData.data);
    }
}

class MyData {
    public volatile int data = 0;
    public void addPlusPlus(){
        data++;
    }
}