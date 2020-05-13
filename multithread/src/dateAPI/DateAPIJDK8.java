package dateAPI;

import org.junit.Test;

import java.sql.SQLOutput;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

/**
 * @program: java.shangguigu.learn
 * @description: JDK8中新增的时间PAI
 * @author: Hao Peng
 * @create: 2019-12-20 19:50
 **/



/*
java.util.Date,在jdk1.2引入Calendar之后就大部分接口过时
但是Calendar并没有比java.util.Date好多少
仍然存在的问题：
可变性：Calendar的日期时间可以通过setTime()进行设置，真正的日期应该不可变
偏移性：Date中的年份是从1900年开始的而月份都是从0开始
        Date date = new Date(2020,9,10);//返回的是3920年10月10号
格式化：格式化只对Date有用，Calendar则不行（SimpleDateFormat只能格式化Date）
此外，它们也不是线程安全的；不能处理闰秒等。

Java8引入了java.time API已经纠正了过去的缺陷，比较成功
java.time中包含了所有关于本地日期（LocalDate）、本地时间（LocalTime）
                      本地日期时间（LocalDateTime）、时区（ZonedDateTime）
                      持续时间（Duration）

新时间日期API
java.time--包含值对象的基础包（常用）
java.time.chrono--提供对不同的日历系统的访问
java.time.format--格式化和解析时间和日期（常用）
java.time.temporal--包括底层框架和扩展特性（可能会涉及）
java.time.zone--包含时区支持的类
 */
public class DateAPIJDK8 {

    /*
    LocalDate、LocalTime、LocalDateTime    类似于java.util中的Calendar类
     */
    @Test
    public void test1(){
        //实例化方式1：调用now()获取当前日期、时间、日期+时间
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDate);//2019-12-20
        System.out.println(localTime);//21:35:11.455063600
        System.out.println(localDateTime);//2019-12-20T21:35:11.455063600

        //实例化方式2：of()，没有偏移量。设定指定的年月日时分秒
        LocalDateTime localDateTime1 = LocalDateTime.of(2020, 10, 3, 9, 41, 22);
        System.out.println(localDateTime1);//2020-10-03T09:41:22
        //getXxx()
        System.out.println(localDateTime.getDayOfMonth());
        System.out.println(localDateTime.getDayOfYear());
        System.out.println(localDate.getDayOfYear());

        //体现不可变性的设置用withXxx
        LocalDateTime localDate1 = localDateTime.withDayOfMonth(17);
        System.out.println(localDate1);//2019-12-17T21:44:33.075393300
        System.out.println(localDateTime);//2019-12-20T21:44:33.075393300

        //plusXxx体现不可变性
        LocalDateTime localDateTime2 = localDateTime.plusDays(2);
        System.out.println(localDateTime);//2019-12-20T21:46:59.926224700
        System.out.println(localDateTime2);//2019-12-22T21:46:59.926224700
    }

    /*
    Instant:类似于java.util中的Date类
    可以获取1900到现在的瞬时时间毫秒

     */
    @Test
    public void test2(){
        Instant now = Instant.now();
        //计算的是本初子午线的时间，我们在东八区时差8小时
        System.out.println(now);//2019-12-20T13:49:57.237458900Z
        //添加时间的偏移量
        OffsetDateTime offsetDateTime = now.atOffset(ZoneOffset.ofHours(8));
        System.out.println(offsetDateTime);//2019-12-20T21:52:01.869753900+08:00
        //toEpochMilli():获取1900年到现在的毫秒数
        long milli = now.toEpochMilli();
        System.out.println(milli);//1576849997025
        //ofEpochMilli:通过传递毫秒数创建对象
        Instant instant1 = Instant.ofEpochMilli(1576849997025L);
        System.out.println(instant1);//2019-12-20T13:53:17.025Z
    }
    /*
    DateTimeFormatter:格式化或解析日期、时间
    类似于DateTimeFormat
     */
    @Test
    public void test3(){
        //方式一：预定义的标准格式。如：ISO_LOCAL_DATE_TIME;ISO_LOCAL_DATE;ISO_LOCAL_TIME(基本不用)
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        //格式化：日期-->字符串
        LocalDateTime now = LocalDateTime.now();
        String str1 = formatter.format(now);
        System.out.println(now);//2019-12-20T22:03:49.217420100
        System.out.println(str1);//2019-12-20T22:03:49.2174201
        //解析：字符串-->日期
        TemporalAccessor parse = formatter.parse("2019-12-20T22:03:49.2174201");
        System.out.println(parse);//{},ISO resolved to 2019-12-20T22:03:49.217420100


        //方式二：本地化相关的格式。如ofLocalizedDateTime(FormatStyle.LONG)（基本不用）

        //方式三：自定义的格式。如：ofPattern("yyyy-MM-dd hh:mm:ss")（常用）
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss E");
        //格式化
        String str2 = formatter1.format(LocalDateTime.now());
        System.out.println(str2);
        //解析
        TemporalAccessor accessor = formatter1.parse("2019-12-20 10:11:02 周五");
        System.out.println(accessor);
    }



}
