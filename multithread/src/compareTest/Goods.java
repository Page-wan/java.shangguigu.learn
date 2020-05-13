package compareTest;

import java.util.zip.DeflaterOutputStream;

/**
 * @program: java.shangguigu.learn
 * @description: 自定义类实现Comparable接口
 * @author: Hao Peng
 * @create: 2019-12-20 22:42
 **/
public class Goods implements Comparable {

    private String name;
    private double price;

    public Goods(){}

    public Goods(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof Goods){
            //方式一：
            Goods goods = (Goods)o;
            if (this.price > goods.price){
                return 1;
            }else if (this.price < goods.price){
                return -1;
            }else {
                return 0;
            }
            //方式二：
            //return Double.compare(this.price,goods.price);
        }
        throw new RuntimeException("传入数据不一致");
    }
}
