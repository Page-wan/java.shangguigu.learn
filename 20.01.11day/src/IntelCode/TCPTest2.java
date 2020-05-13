package IntelCode;

import org.junit.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @program: java.shangguigu.learn
 * @description: tcp协议测试2
 * @author: Hao Peng
 * @create: 2020-01-12 16:04
 **/
public class TCPTest2 {

    @Test
    public void client() {
        Socket socket = null;
        OutputStream os = null;
        FileInputStream fis = null;
        try {
            socket = new Socket(InetAddress.getByName("127.0.0.1"), 9999);
            os = socket.getOutputStream();
            fis = new FileInputStream("10.png");
            byte[] buff = new byte[20];
            int len ;
            while ((len = fis.read(buff)) != -1){
                os.write(buff, 0, len);
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

    @Test
    public  void server(){
        ServerSocket ss = null;
        Socket socket = null;
        InputStream is = null;
        FileOutputStream fos = null;
        try {
            ss = new ServerSocket(9999);
            socket = ss.accept();
            is = socket.getInputStream();
            fos = new FileOutputStream("11.png");
            byte[] buff = new byte[20];
            int len;
            while ((len = is.read(buff)) != -1){
                fos.write(buff, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null){
                try {
                    fos.close();
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
            if (ss !=null){
                try {
                    ss.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
