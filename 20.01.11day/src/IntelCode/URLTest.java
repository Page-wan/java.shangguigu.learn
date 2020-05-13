package IntelCode;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @program: java.shangguigu.learn
 * @description: URL网络编程
 * @author: Hao Peng
 * @create: 2020-01-12 17:13
 **/
public class URLTest {
    public static void main(String[] args) {
        try {
            URL url = new URL("http://localhost:8080/example/beauty.jpg");
            System.out.println(url.getProtocol());
            System.out.println(url.getHost());
            System.out.println(url.getPort());
            System.out.println(url.getPath());
            System.out.println(url.getFile());
            System.out.println(url.getQuery());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
