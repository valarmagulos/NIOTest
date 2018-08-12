package BIO;

import java.net.Socket;
import java.nio.ByteBuffer;

/**
 * description:
 * create: 2018-08-12
 *
 * @author zhun.huang
 */
public class MyProtocol {

    public static final String END_DELIMITER = "$^$";

    public static String read(Socket socket) {
        byte[] bytes = new byte[1024];
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
        try {
            int read = socket.getInputStream().read(bytes);
            byteBuffer.position(read);
            while (!new String(byteBuffer.array(), 0, read).endsWith(END_DELIMITER)) {
                System.out.println("协议包当前已经读取内容:" + new String(byteBuffer.array(), 0, read));
                byte[] remains = new byte[1024];
                int read1 = socket.getInputStream().read(remains);
                read = read1 + read;
                byteBuffer.put(remains,0,read1);
            }
        } catch (Exception ex) {
            System.err.println("读取异常");
        }
        System.out.println("单个协议包读取完毕，　内容：" + new String(byteBuffer.array()));
        String response = new String(byteBuffer.array());
        return response.substring(0, response.indexOf(END_DELIMITER));
    }

    public static void write(Socket socket, String content) {
        try {
            socket.getOutputStream().write(content.getBytes());
            socket.getOutputStream().write(END_DELIMITER.getBytes());
            socket.getOutputStream().flush();
        } catch (Exception e) {
            System.err.println("输出异常");
        }
    }

    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put("hello".getBytes());
        byteBuffer.put("world".getBytes());
        System.out.println(new String(byteBuffer.array()));
    }
}
