package threadTest1;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @program: java.shangguigu.learn
 * @description: 用Callable创建线程
 * @author: Hao Peng
 * @create: 2019-12-16 19:53
 **/

//1、创建一个Callable的实现类
class NumThread implements Callable{
    //2、实现call方法，将此线程需要执行的操作写在此方法中
    @Override
    public Object call() throws Exception {
        int sum = 0;
        for (int i = 0; i <= 100; i++) {
            if (i % 2 == 0){
                System.out.println(i);
                sum +=i;
            }
        }
        return sum;
    }
}

public class ThreadByCallable {
    public static void main(String[] args){
        //3、创建Callable接口实现类的对象
        NumThread numThread = new NumThread();
        //4、将此Callable接口实现类的对象作为传递到FutureTask构造器中，创建FutureTask对象
        FutureTask futureTask = new FutureTask(numThread);
        //5、将FutureTask的对象作为参数传递到Thread类的构造器中，并调用start（）
        new Thread(futureTask).start();
        try {
            //get()返回值即为FutureTask构造器参数Callable实现类重写的call方法的返回值
            Object sum = futureTask.get();
            System.out.println(sum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
