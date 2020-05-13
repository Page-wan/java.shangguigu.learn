package compareTest;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @program: java.shangguigu.learn
 * @description: Comparator方法测试
 * @author: Hao Peng
 * @create: 2019-12-21 19:21
 **/
public class ComparatorTest {
    @Test
    public void test1(){
        String[] arr = new String[]{"aa","ff","zz","bb"};
        Arrays.sort(arr, new Comparator(){
            //按照字符串从大到小的顺序排列
            @Override
            public int compare(Object o, Object t1) {
                if (o instanceof String && t1 instanceof String){
                    String s1 = (String) o;
                    String s2 = (String) t1;
                    return -s1.compareTo(s2);
                }
                throw new RuntimeException("输入数据异常");
            }
        });
        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void test2(){
        Goods[] arr1 = new Goods[4];
        arr1[0] = new Goods("LenovoMouse",35);
        arr1[1] = new Goods("DellMouse",43);
        arr1[2] = new Goods("LogitechMouse",443);
        arr1[3] = new Goods("LogitechMouse",1999);
        Arrays.sort(arr1, new Comparator<Goods>() {
            //指定排序方式，先按照商品名称从低到高，再价格从高到低
            @Override
            public int compare(Goods goods, Goods t1) {
                if (goods.getName().equals(t1.getName())){
                    return -Double.compare(goods.getPrice(),t1.getPrice());
                }else {
                    return goods.getName().compareTo(t1.getName());
                }
            }
        });
        System.out.println(Arrays.toString(arr1));
    }
}
