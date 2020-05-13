package FileStream;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * @program: java.shangguigu.learn
 * @description: File类测试
 * @author: Hao Peng
 * @create: 2020-01-08 15:45
 **/
public class FileTest {
    @Test
    public void tets1(){
        File file1 = new File("D:\\JAVA_project\\java.shangguigu.learn\\20.01.08day\\hello.txt");
        System.out.println(file1.getAbsolutePath());//返回绝对路径
        System.out.println(file1.getPath());//返回创建file对象时的路径
        System.out.println(file1.getName());//返回文件名称
        System.out.println(file1.getParent());//当前文件的上一级目录
        System.out.println(file1.length());//返回该文件对象对应文件的字节长度
        System.out.println(file1.lastModified());//最近修改时间
    }
    @Test
    public void test2(){
        //如下两个方法适用于文件目录
        File file2 = new File("D:\\JAVA_project\\java.shangguigu.learn");
        String[] list = file2.list();//返回文件或目录的列表，字符串形式返回，且要求file2对应路径确实存在
        for (String s :
                list) {
            System.out.println(s);
        }
        File[] files = file2.listFiles();//以绝对路径返回文件或目录的列表，是File类型对象
        for (File f :
                files) {
            System.out.println(f);
        }
    }
    @Test
    public void test3(){
        //要求file1在磁盘中存在，file2不能在磁盘中存在
        File file1 = new File("hello.txt");
        File file2 = new File("C:\\Users\\ph\\Desktop\\1\\hi.txt");
        boolean b = file1.renameTo(file2);//将file1移动到file2路径下。
        System.out.println(b);
    }
    @Test
    public void test4(){
        File file = new File("C:\\Users\\ph\\Desktop\\1\\hi.txt");
        boolean isDirectory = file.isDirectory();//判断是否是文件目录
        boolean isfile = file.isFile();//判断是否是文件 false
        boolean exists = file.exists();//判断是否存在 true
        boolean b = file.canRead();//判断是否可读 true
        boolean b1 = file.canWrite();//判断是否可写 true
        boolean hidden = file.isHidden();//判断是否可隐藏 false
    }
    @Test//创建/删除文件
    public void test5() throws IOException {
        File file = new File("hi.txt");
        if (!file.exists()){
            file.createNewFile();
            System.out.println("文件创建成功");
        }else {
            file.delete();
            System.out.println("文件删除成功");
        }
    }
    @Test//创建/删除文件目录
    public void test6(){
        File file = new File("C:\\Users\\ph\\Desktop\\1\\io");
        if (!file.exists()){
            //上层目录不存在忽略创建用mkdir();;上层目录不存在一并创建用mkdirs()
            file.mkdirs();
            System.out.println("目录创建成功");
        }else {
            file.delete();//删除文件，只会删除一层
            System.out.println("删除成功");
        }
    }
}
