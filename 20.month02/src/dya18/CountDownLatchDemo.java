package dya18;

import java.util.concurrent.CountDownLatch;

/**
 * @program: java.shangguigu.learn
 * @description: countDowmLatch测试
 * @author: Hao Peng
 * @create: 2020-02-18 20:08
 **/
public class CountDownLatchDemo {

    public static void main(String[] args) throws Exception{
        CountDownLatch countDownLatch = new CountDownLatch(5);
        for (int i = 1; i <= 5; i++){
            new Thread(() -> {
                System.out.println("同学" + Thread.currentThread().getName() + "\t 走出教室");
                countDownLatch.countDown();
            },CountryEnum.forEach_CountryEnum(i).getRetCountry()).start();
        }
        countDownLatch.await();
        System.out.println("******" + Thread.currentThread().getName() + "****班长走出教室");
    }
}

enum CountryEnum {
    ONE(1,"齐"),
    TWO(2,"楚"),
    THREE(3,"燕"),
    FOUR(4,"赵"),
    FIVE(5,"魏"),
    SIX(6,"韩");

    private Integer retNumber;
    private String retCountry;

    public String getRetCountry() {
        return retCountry;
    }

    public void setRetNumber(Integer retNumber) {
        this.retNumber = retNumber;
    }

    public void setRetCountry(String retCountry) {
        this.retCountry = retCountry;
    }

    CountryEnum(Integer retNumber, String retCountry) {
        this.retNumber = retNumber;
        this.retCountry = retCountry;
    }

    public Integer getRetNumber() {
        return retNumber;
    }

    public static CountryEnum forEach_CountryEnum(int index){
        CountryEnum[] myArray = CountryEnum.values();
        for (CountryEnum element : myArray) {
            if (index == element.getRetNumber()){
                return element;
            }
        }
        return null;
    }
}

