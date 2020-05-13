package java2;

import org.junit.Test;
import reflectionTest.Person;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @program: java.shangguigu.learn
 * @description: 反射获取指定属性
 * @author: Hao Peng
 * @create: 2020-01-15 16:20
 **/
public class ReflectionTest {

    @Test
    public void test1() throws NoSuchFieldException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class clazz = Person.class;
        //获取指定的属性,要求属性声明为public（一般不用此方法）
        Field id = clazz.getField("id");
        //获取运行时类的对象
        Person p = (Person) clazz.getDeclaredConstructor().newInstance();
        id.set(p,1001);
        System.out.println((int)id.get(p));
    }
    @Test
    public void test2()throws Exception {
        Class clazz = Person.class;
        //获取指定的属性,开发中真正用的
        Field id = clazz.getDeclaredField("id");
        Person p = (Person) clazz.getDeclaredConstructor().newInstance();
        id.setAccessible(true);//需要设置为true才能对非public属性进行设置
        //id.set();id.get()
    }

    @Test
    public void test3()throws Exception {
        Class clazz = Person.class;
        //获取运行时类的对象
        Person p1 = (Person) clazz.getDeclaredConstructor().newInstance();
        //getDeclaredMethod():参数一：指明获取的方法的名称；参数二：指明方法的形参列表
        Method show = clazz.getDeclaredMethod("show", String.class);
        show.setAccessible(true);//同理能操作方法,保证方法可访问
        //invoke(),参数一：调用方法的对象，参数二：给方法形参赋值的实参
        //invoke()的返回值与对应调用方法的返回值，默认Object
        Object returnValue = show.invoke(p1, "CHN");
        System.out.println(returnValue);

        //调用静态方法private static void showDesc()
        Method showDesc = clazz.getDeclaredMethod("showDesc");
        showDesc.setAccessible(true);
        Object returnValue1 = showDesc.invoke(clazz);//或者是Person.class
        System.out.println(returnValue1);
    }
    @Test
    public void test4()throws Exception {
        Class clazz = Person.class;
        //获取指定参数列表的构造器
        Constructor constructor = clazz.getDeclaredConstructor(String.class);
        constructor.setAccessible(true);
        Person p1 = (Person) constructor.newInstance("Tom");
    }
}
