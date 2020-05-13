package reflectionTest;

import org.junit.Test;

import java.util.Random;

/**
 * @program: java.shangguigu.learn
 * @description: Class类方法测试
 * @author: Hao Peng
 * @create: 2020-01-13 16:34
 **/
public class ClassTest {
    @Test
    public void test1() throws ClassNotFoundException {
        //实际中使用频率3>1>2
        //方式一：通过运行时类的属性.class
        Class clazz1 = Person.class;
        System.out.println(clazz1);
        //方式二：通过运行时类的对象，调用getClass()
        Person p1 = new Person();
        Class clazz2 = p1.getClass();
        System.out.println(clazz2);
        //方式3：调用Class的静态方法：forName(String classPath)
        Class clazz3 = Class.forName("reflectionTest.Person");
        System.out.println(clazz3);
        //方式四：使用类的加载器：ClassLoader(了解)
        ClassLoader classLoader = ClassTest.class.getClassLoader();
        Class clazz4 = classLoader.loadClass("reflectionTest.Person");
        System.out.println(clazz4);
    }

    @Test
    public void test3() throws Exception {
        Class<Person> clazz = Person.class;
        /*
        clazz.newInstance(),调用此方法创建对应的运行时类的对象
        要想此方法正常创建运行时类对象，提供空参构造器、访问权限够，设置为public
        因此在javabean中要求提供一个空参构造器，原因：
        1.便于通过反射，创建运行时类的对象
        2.便于子类继承此运行时，默认调用super()时，保证父类有此构造器
         */
        Person p1 = clazz.newInstance();
    }

    @Test
    public void test4(){
        for (int i = 0; i < 100; i++) {
            int num = new Random().nextInt(3);
            String classPath = "";
            switch (num){
                case 0:
                    classPath = "java.util.Date";
                    break;
                case 1:
                    classPath = "java.lang.Object";
                    break;
                case 2:
                    classPath = "reflectionTest.Person";
                    break;
            }
            try {
                Object obj = getInstance(classPath);
                System.out.println(obj);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Object getInstance(String classPath) throws Exception {
        Class clazz = Class.forName(classPath);
        return clazz.newInstance();
    }
}
