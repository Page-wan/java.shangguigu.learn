package dateAPI;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @program: java.shangguigu.learn
 * @description: 时间日期测试
 * @author: Hao Peng
 * @create: 2019-12-19 20:26
 **/
public class DateTimeAPI {

    @Test
    public void test1(){
        long time = System.currentTimeMillis();
        System.out.println(time);
    }
    @Test
    public void test2() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat();
        //格式化日期：日期——>转字符串
        Date date = new Date();
        System.out.println(date);
        String format = sdf.format(date);
        //2019/12/19 下午10:18  字符串
        System.out.println(format);
        //解析：字符串-->日期
        //要求字符串格式：2019/12/19 下午10:18
        String str = "2019/12/19 下午10:18";
        //会抛异常
        Date date1 = sdf.parse(str);//Unparseable date: "2019-08-09"
        System.out.println(date1);
        //换一种构造器，避免上述不喜欢的格式,文档中去找想要的格式
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy.MMMMM.dd GGG hh:mm aaa");
        //2019.十二月.19 公元 10:25 下午
        String format1 = sdf1.format(date);
        System.out.println(format1);

    }

    @Test
    public void test3() throws ParseException {
        String birth = "2020-09-08";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(birth);
        java.sql.Date birthDate = new java.sql.Date(date.getTime());
        System.out.println(birthDate);//out:2020-09-08
    }

}
