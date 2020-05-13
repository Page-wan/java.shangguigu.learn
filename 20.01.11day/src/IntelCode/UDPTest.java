package IntelCode;

import org.junit.Test;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * @program: java.shangguigu.learn
 * @description: UDP测试
 * @author: Hao Peng
 * @create: 2020-01-12 16:52
 **/
public class UDPTest {

    //发送端
    @Test
    public void sender(){
        DatagramSocket so = null;
        try {
            so = new DatagramSocket();
            String str = "我是UDP协议发送的信息";
            byte[] data = str.getBytes();
            InetAddress host = InetAddress.getLocalHost();
            DatagramPacket packet = new DatagramPacket(data, 0, data.length, host, 8899);
            so.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (so !=null){
                try {
                    so.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //接收端
    @Test
    public void receiver(){
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket(8899);
            byte[] data = new byte[100];
            DatagramPacket packet = new DatagramPacket(data, 0, data.length);
            socket.receive(packet);
            System.out.println(new String(packet.getData(), 0, packet.getLength()));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket != null){
                try {
                    socket.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
