package NIO;

import BIO.MyProtocol;
import common.Constant;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * description:
 * create: 2018-08-12
 * java nio
 *
 * @author zhun.huang
 */
public class Client {

    public static void main(String[] args) throws IOException {
        SocketAddress socketAddress = new InetSocketAddress(Constant.serverHost, Constant.serverPort);
        while (System.in.read() != 0) {
            System.out.println("尝试从服务器校准当前时间...");
            Socket socket = new Socket();
            socket.connect(socketAddress);
            MyProtocol.write(socket,Constant.TimeAction);
            String read = MyProtocol.read(socket);
            System.out.println("服务器返回信息:" + read);
        }
    }
}
