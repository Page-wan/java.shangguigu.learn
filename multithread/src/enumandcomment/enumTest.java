package enumandcomment;

/**
 * @program: java.shangguigu.learn
 * @description: 枚举类测试
 * @author: Hao Peng
 * @create: 2019-12-21 20:44
 **/
public class enumTest {
    public static void main(String[] args) {
        //创建对象的方式
        Season season = Season.WINTER;
    }
}
//JDK5.0之前
class Season{
    //1、声明Season对象的属性：private final
    private final String seasonNme;
    private final String seasonDesc;

    //2、私有化类的构造器，并给对象属性赋值
    private Season(String seasonNme,String seasonDesc){
        this.seasonNme = seasonNme;
        this.seasonDesc = seasonDesc;
    }
    //3、提供当前枚举类的多个对象，public static final
    public static final Season SPRING = new Season("春天", "穿暖花开");
    public static final Season SUMMER = new Season("夏天", "夏日炎炎");
    public static final Season AUTUMN = new Season("秋天", "秋高气爽");
    public static final Season WINTER = new Season("冬天", "冰天雪地");
    //4、提供属性get（）方法,toString()等
}
//JDK5.0
//1、声明enum枚举类
//定义的枚举类默认继承于java.lang.enum
enum Season2{
    //1、提供当前枚举类的对象，多个对象之间用“,”隔开，末尾用“;”结束
    SPRING("春天", "穿暖花开"),
    SUMMER("夏天", "夏日炎炎"),
    AUTUMN("秋天", "秋高气爽"),
    Season("冬天", "冰天雪地");
    //3、声明Season对象的属性：private final
    private final String seasonNme;
    private final String seasonDesc;
    //4、私有化类的构造器，并给对象属性赋值
    private Season2(String seasonNme,String seasonDesc){
        this.seasonNme = seasonNme;
        this.seasonDesc = seasonDesc;
    }
    //5、相关get方法。此外不需要重写toString()方法
}
