package dateAPI;

import org.junit.Test;

import java.security.PublicKey;
import java.util.Calendar;
import java.util.Date;

/**
 * @program: java.shangguigu.learn
 * @description: 日历类测试
 * @author: Hao Peng
 * @create: 2019-12-20 18:38
 **/
public class CalendarTest {

    /*

    calendar日历类（抽象类）的使用
    注意：获取月份时，一月是0，十二月是11
         获取星期时，周日是1，周一是2，。。。周六是七
     */
    @Test
    public void test1(){
        //1、实例化
        //方式一：创建其子类（GregorianCalendar）的对象，一般不这么创建
        //方式二：调用其静态方法getInstance()
        Calendar calendar = Calendar.getInstance();

        //2、常用方法
        //get(),获取Calendar的一些静态属性，年，月，日等
        int days = calendar.get(Calendar.DAY_OF_MONTH);
        System.out.println(days);
        System.out.println(calendar.get(Calendar.MONTH));
        //set()可以对Calendar对象的属性（年月日等）进行修改
        //Calendar是可变的
        calendar.set(Calendar.DAY_OF_MONTH,10);
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));
        //add()同set(),只是只能添加
        calendar.add(Calendar.DAY_OF_MONTH,7);
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));
        //getTime():将日历类转换乘Date类对象
        Date time = calendar.getTime();
        System.out.println(time);
        //setTime():Date类的对象转换成日历类
        Date date1 = new Date();
        calendar.setTime(date1);
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));
    }
}
