package generic.first;

import org.junit.Test;

import java.util.*;

/**
 * @program: java.shangguigu.learn
 * @description: 泛型测试
 * @author: Hao Peng
 * @create: 2020-01-07 17:37
 **/
public class GernericTest {
   @Test
   public void test1(){
       //添加泛型之前
       List list = new ArrayList();
       list.add(123);
       list.add("dw");
   }
   //自然排序的泛型示例
   @Test public void test2(){
       TreeSet<Person> list = new TreeSet<>();
       list.add(new Person("huangxiaoming", 39));
       list.add(new Person("jindong", 42));
       list.add(new Person("chengweiting", 31));
       list.add(new Person("wuyifan", 28));
       Iterator<Person> iterator = list.iterator();
       while (iterator.hasNext()){
           System.out.println(iterator.next());
       }
   }
    //定制排序的泛型示例
    @Test
    public void test3(){
        TreeSet<Student> list = new TreeSet<>(new Comparator<Student>() {
            @Override
            public int compare(Student student, Student t1) {
                return student.getName().compareTo(t1.getName());
            }
        });
        list.add(new Student("huangxiaoming", 39));
        list.add(new Student("jindong", 42));
        list.add(new Student("chengweiting", 31));
        list.add(new Student("wuyifan", 28));
//        Iterator<Student> iterator = list.iterator();
//        while (iterator.hasNext()){
//            System.out.println(iterator.next());
//        }
        list.forEach(System.out::println);
    }

}

class Person implements Comparable<Person>{
    private String name;
    private int age;
    public Person() {
    }
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public int compareTo(Person person) {
        return Integer.compare(this.age,person.age);
    }
}

class Student{
    private String name;
    private int id;

    public Student(String name, int id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }

    public Student() {
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}