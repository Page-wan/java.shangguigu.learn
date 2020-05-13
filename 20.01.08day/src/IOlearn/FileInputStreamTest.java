package IOlearn;

import org.junit.Test;

import java.io.*;

/**
 * @program: java.shangguigu.learn
 * @description: 字节输入测试
 * @author: Hao Peng
 * @create: 2020-01-08 21:20
 **/
public class FileInputStreamTest {

    @Test
    public void test1() {
        FileReader fr = null;
        try {
            //1、实例化File类的对象，指明要操作的文件
            File file = new File("hello.txt");
            //2、提供具体的流
            fr = new FileReader(file);
            //3、数据的读入
            //方法一：效率低
//            int read;
//            while ((read = fr.read()) != -1){
//                System.out.print((char) read);
//            }
            //方法二：
            char[] cbuf = new char[5];
            int len;
            while ((len = fr.read(cbuf)) != -1){
                for (int i = 0; i < len; i++) {
                    System.out.print(cbuf[i]);
                }
                //方法三：
//                String str = new String(cbuf,0,len);
//                System.out.print(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            //4、流的关闭操作
            try {
                if (fr != null)
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testFileWriter() throws IOException {
        //1.提供file类的对象，指明写出到文件
        File file = new File("hello.txt");
        //2.提供FileWriter的对象，用于数据的写出
        FileWriter fw = new FileWriter(file);
        //FileWriter fw = new FileWriter(file,false);
        //FileWriter fw = new FileWriter(file,true);
        //3.写出的操作
        fw.write("asdasfa2");
        fw.write("sfddddddddd2");
        //4.流资源的关闭
        fw.close();
    }

    @Test
    public void testFileInoutStream() {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            //1.造文件
            File file = new File("out_img0.jpg");
            File file2 = new File("out_img1.jpg");
            //造流
            fis = new FileInputStream(file);
            fos = new FileOutputStream(file2);

            byte[] bbuf = new byte[5];
            int len;
            while ((len = fis.read(bbuf)) != -1){
                for (int i = 0; i < len; i++) {
                    fos.write(bbuf[i]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    @Test
    public void test5() {
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            //1.造文件
            File file1 = new File("out_img0.jpg");
            File file2 = new File("out_img3.jpg");
            //2.造节点流（字节流/字符流）
            FileInputStream fis = new FileInputStream(file1);
            FileOutputStream fos = new FileOutputStream(file2);
            //3.造缓冲流
            bis = new BufferedInputStream(fis);
            bos = new BufferedOutputStream(fos);
            //4.具体的复制
            byte[] buffer = new byte[10];
            int len;
            while ((len = bis.read(buffer)) != -1){
                bos.write(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //5.流关闭
            //要求：先关闭外层流，再关闭内层流
            //说明：关闭外层流的同时，会自动帮助关闭内层流
            if (bos != null){
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bis != null){
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            //        fos.close();
            //        fis.close();
        }
    }

    @Test
    public void test6() {
        BufferedReader bfr = null;
        BufferedWriter bfw = null;
        try {
            bfr = new BufferedReader(new FileReader(new File("hello.txt")));
            bfw = new BufferedWriter(new FileWriter(new File("hello2.txt")));
            //方式一：
//            char[] cbuf = new char[8196];
//            int len;
//            while ((len = bfr.read(cbuf)) != -1){
//                bfw.write(cbuf,0,len);
//            }
            //方式二：
            String str;
            while ((str = bfr.readLine()) != null){
                //方法一：
//                bfw.write(str + "\n");
                //方法二：
                bfw.write(str);
                bfw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bfr != null){
                try {
                    bfr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bfw != null){
                try {
                    bfw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    /*
    此处异常还是应该用 try-catch
     */
    public void test7() throws IOException {
        FileInputStream fis = new FileInputStream("hello.txt");
        InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
        char[] cbuf = new char[20];
        int len;
        while ((len = isr.read(cbuf)) != -1){
            String str = new String(cbuf,0,len);
            System.out.println(str);
        }
        isr.close();
    }

    @Test
    public void test8() throws IOException {
        //1
        DataOutputStream dos = new DataOutputStream(new FileOutputStream("data.txt"));
        //2
        dos.writeUTF("彭浩");
        dos.flush();
        dos.writeInt(22);
        dos.writeBoolean(true);
        dos.flush();
        //3
        dos.close();
    }

    @Test
    public void test9() throws IOException {
        //注意：读取时，数据要求与存储一致
        //1
        DataInputStream dis = new DataInputStream(new FileInputStream(("data.txt")));
        //2
        String str = dis.readUTF();
        int data = dis.readInt();
        boolean aBoolean = dis.readBoolean();
        System.out.println("name:" + str + ",age:" + data + ",famle+" + aBoolean);
        //3
        dis.close();
    }

    //序列化
    @Test
    public void test11(){
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream("mydata.dat"));
            oos.writeObject(new String("东北人唱东北人的歌"));
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (oos != null){
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //反序列化
    @Test
    public void test12(){
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream("mydata.dat"));
            Object o = ois.readObject();
            String s = (String) o;
            System.out.println(s);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (ois != null){
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    @Test
    public void test13() {
        RandomAccessFile raf1 = null;
        RandomAccessFile raf2 = null;
        try {
            raf1 = new RandomAccessFile(new File("out_img0.jpg"), "r");
            raf2 = new RandomAccessFile(new File("out_imgs.jpg"), "rw");
            byte[] buff = new byte[1024];
            int len;
            while ((len = raf1.read(buff)) != -1){
                raf2.write(buff, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (raf1 != null){
                try {
                    raf1.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (raf2 != null){
                try {
                    raf2.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public  void test14() throws IOException {
        RandomAccessFile raf = new RandomAccessFile("hello.txt", "rw");
        raf.seek(3);
        StringBuilder builder = new StringBuilder((int) new File("hello.txt").length());
        byte[] buff = new byte[20];
        int len;
        while ((len = raf.read(buff)) != -1){
            builder.append(new String(buff, 0, len));
        }
        raf.seek(3);
        raf.write("xyz".getBytes());
        raf.write(builder.toString().getBytes());
        raf.close();
    }
}
