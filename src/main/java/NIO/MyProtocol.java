package NIO;

import common.Constant;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * description:
 * create: 2018-08-12
 *
 * @author zhun.huang
 */
public class MyProtocol {

    public static void write(SocketChannel socketChannel, String content){
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put(content.getBytes());
        byteBuffer.put(Constant.endDelimeter.getBytes());
        byteBuffer.flip();
        try {
            socketChannel.write(byteBuffer);
        } catch (IOException e) {
            System.out.println("输出异常");
        }
    }

    public static String read(SocketChannel socketChannel) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        try {
            int read = socketChannel.read(byteBuffer);
            while (! new String(byteBuffer.array(),0,read).endsWith(Constant.endDelimeter)) {
                System.out.println("协议包当前已经读取内容:" + new String(byteBuffer.array(), 0, read));
                int read1 = socketChannel.read(byteBuffer);
                read = read1 + read;
            }
            System.out.println("单个协议包读取完毕，　内容：" + new String(byteBuffer.array()));
            return new String(byteBuffer.array(),0,read).substring(0,new String(byteBuffer.array(),0,read).indexOf(Constant.endDelimeter));
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }
    }
}
