package test;

/**
 * @program: java.shangguigu.learn
 * @description:
 * @author: Hao Peng
 * @create: 2019-12-09 21:54
 **/
//第一步：创建实现Runnable接口的类
class MyThread implements Runnable{
    //第二步：重写Runnable接口的run方法
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0){
                System.out.println(i);
            }
        }
    }
}
public class RunableTest {
    public static void main(String[] args) {
        //第三步：创建实现类的对象
        MyThread myThread = new MyThread();
        //第四步：将第三步创建的对象作为参数传递到Thread类的构造器中创建Thread类的对象
        Thread t1 = new Thread(myThread);
        //第五步：调用start（）方法
        t1.start();
    }
}
