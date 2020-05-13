package java2;

import org.junit.Test;
import reflectionTest.Person;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;

/**
 * @program: java.shangguigu.learn
 * @description: ceshi
 * @author: Hao Peng
 * @create: 2020-01-15 15:04
 **/
public class FieldTest {
    @Test
    public void test1(){
        Class clazz = Person.class;
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field f :
                declaredFields) {
            //1.权限修饰符
            int modifier = f.getModifiers();
            System.out.print(Modifier.toString(modifier) + "\t");

            //2.数据类型
            Class type = f.getType();
            System.out.print(type.getName() + "\t");

            //3.变量名
            String fName = f.getName();
            System.out.println(fName);

            System.out.println();
        }
    }

    @Test
    public void test2(){
        Class clazz = Person.class;
        //getMethods():获取当前运行类及其所有父类中声明为public权限的方法
        Method[] methods = clazz.getMethods();
        for (Method m :
                methods) {
            System.out.println(m);
        }
        //getDeclaredMethods():获取当前运行时类中声明的所有方法，不包含父类中声明
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method m :
                declaredMethods) {
            System.out.println(m);
        }
    }

    @Test
    public void test3() {
        Class clazz = Person.class;
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method m :declaredMethods){
            /*
            获取方法声明的注解
            1.注解有多个，因此为数组
            2.获取的是Runtime修饰的注解
             */
            Annotation[] annotations = m.getAnnotations();
            for (Annotation anno : annotations){
                System.out.println(anno);
            }
        }
    }
    
    @Test
    public void test4(){
        Class clazz = Person.class;
        //getConstructors():获取当前运行时类中声明为public的构造器
        Constructor[] constructors = clazz.getConstructors();
        for (Constructor cons : constructors){
            System.out.println(cons);
        }
        //getDeclaredConstructors():同理，获取当前运行时类中声明的所有构造器
    }

    @Test
    public void test5(){
        Class clazz = Person.class;
        //getSuperclass()：获取当前运行时类的父类
        Class superclass = clazz.getSuperclass();
        System.out.println(superclass);
        //getGenericSuperclass()：获取当前运行时类的带泛型的父类
        Type genericSuperclass = clazz.getGenericSuperclass();
        System.out.println(genericSuperclass);
        //获取父类所带的泛型
        ParameterizedType par = (ParameterizedType) genericSuperclass;
        Type[] actualTypeArguments = par.getActualTypeArguments();
        System.out.println(((Class) actualTypeArguments[0]).getName());
    }
    @Test
    public void test6() {
        Class clazz = Person.class;
        Class[] interfaces = clazz.getInterfaces();
        for (Class c : interfaces) {
            System.out.println(c);
        }
        //获取父类实现的结构
        Class[] interfaces1 = clazz.getSuperclass().getInterfaces();
        //foreach
    }
}
