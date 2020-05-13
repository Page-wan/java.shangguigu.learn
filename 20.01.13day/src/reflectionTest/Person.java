package reflectionTest;

/**
 * @program: java.shangguigu.learn
 * @description: 自定义Person类
 * @author: Hao Peng
 * @create: 2020-01-13 16:35
 **/
public class Person {

    public String name;
    int age;
    private int id;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Person() {
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
    private void show(){
        System.out.println("你好，我是一个人");
    }
    private static void showDesc(){
        System.out.println("你好我是静态方法");
    }
}
