package IntelCode;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @program: java.shangguigu.learn
 * @description: 网络编程测试
 * @author: Hao Peng
 * @create: 2020-01-12 15:07
 **/
public class SocketTest {


    //客户端
    @Test
    public void test1() {
        Socket socket = null;
        OutputStream os = null;
        try {
            //创建socket对象，指明服务器端IP和端口号
            InetAddress inet = InetAddress.getByName("127.0.0.1");
            socket = new Socket(inet, 8089);
            //获取一个输出流，用于输出数据
            os = socket.getOutputStream();
            //写出数据操作
            os.write("我有信息要发送到服务端".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //资源的关闭
            if (os != null){
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (socket != null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //服务端
    @Test
    public void test2() {
        ServerSocket ss = null;
        Socket socket = null;
        InputStream is = null;
        ByteArrayOutputStream baos = null;
        try {
            //创建服务器短的socket,指明自己的端口号
            ss = new ServerSocket(8089);
            //调用accept方法，表示接受来自客户端的数据
            socket = ss.accept();
            //获取输入流
            is = socket.getInputStream();
            //读取输入流中的数据，用到ByteArrayOutputStream类防止乱码
            baos = new ByteArrayOutputStream();
            byte[] buff = new byte[20];
            int len;
            while ((len = is.read(buff)) != -1){
                baos.write(buff,0,len);
            }
            System.out.println(baos.toString());
            //获取服务端的IP
            System.out.println("收到了来自：" + socket.getInetAddress().getHostAddress() + "的信息");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //资源关闭
            if (baos != null){
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (is != null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (socket !=null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (ss != null){
                try {
                    ss.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
