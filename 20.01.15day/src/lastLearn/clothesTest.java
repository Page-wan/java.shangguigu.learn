package lastLearn;

/**
 * @program: java.shangguigu.learn
 * @description: 衣服代理
 * @author: Hao Peng
 * @create: 2020-01-16 08:46
 **/


interface  ClothesFactory {
    public String produceClothes();
}

class NIKE implements ClothesFactory {

    @Override
    public String produceClothes() {
        return "生产nike球鞋，AJ球鞋";
    }
}

//class ProxyFactory {
//    public static Object getProxyInstance(Object obj){
//        MyInvocationHandler handler = new MyInvocationHandler();
//        handler.bind(obj);
//        return Proxy.newProxyInstance(obj.getClass().getClassLoader(),obj.getClass().getInterfaces(),handler);
//    }
//}
//class MyInvocationHandler implements InvocationHandler{
//    Object obj;
//    public void bind(Object obj){
//        this.obj = obj;
//    }
//    @Override
//    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
//        Object returnValue = method.invoke(obj, objects);
//        return returnValue;
//    }
//}

public class clothesTest {
}
