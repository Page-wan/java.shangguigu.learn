package lastLearn;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @program: java.shangguigu.learn
 * @description: 动态代理
 * @author: Hao Peng
 * @create: 2020-01-15 17:38
 **/
/*
动态代理的举例
 */
interface Human{
    String getBelief();
    void eat(String food);
}
//被代理类
class SuperMan implements Human{
    @Override
    public String getBelief() {
        return "I can fly";
    }
    @Override
    public void eat(String food) {
        System.out.println("我喜欢吃" + food);
    }
}

/*
生成动态代理需要解决的问题
1.如何根据加载到内存中的被代理类，动态的创建一个代理类机器对象
2.当通过代理类的对象调用方法时，如何动态的去调用被代理类中的同名方法
 */

class ProxyFacotry{
    //调用此方法，返回一个代理类的对象，解决问题1
    //Object的原因是可能代理多个
    public static Object getProxyInstance(Object obj){//obj:被代理类的对象
        MyInvocationHandler handler = new MyInvocationHandler();
        handler.bind(obj);//传入被代理类对象
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(),obj.getClass().getInterfaces(),handler);
    }
}
class MyInvocationHandler implements InvocationHandler{//处理程序
    private Object obj;//需要被代理类的对象进行赋值
    public void bind(Object obj){
        this.obj = obj;
    }
    //当我们通过代理类的对象，调用方法a时，就会自动的调用如下的方法：invoke()
    //将被代理类要执行的方法a的功能就声明在invoke()中
    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        //method：即为代理类对象调用的方法，此方法也就作为了被代理类对象要调用的方法
        //obj:被代理类的对象
        Object returnValue = method.invoke(obj, objects);
        return returnValue;
    }
}

public class ProxyTest {
    public static void main(String[] args) {
        SuperMan superMan = new SuperMan();
        Human proxyInstance = (Human) ProxyFacotry.getProxyInstance(superMan);
        //当通过代理类对象调用方法时，会自动的调用被代理类中同名的方法
        String str = proxyInstance.getBelief();
        System.out.println(str);
        proxyInstance.eat("四川麻辣烫");
        System.out.println("******************");
        NIKE nike = new NIKE();
        ClothesFactory proxyInstance1 = (ClothesFactory) ProxyFacotry.getProxyInstance(nike);
        String str1 = proxyInstance1.produceClothes();
        System.out.println(str1);
    }
}
