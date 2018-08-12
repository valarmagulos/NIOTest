package BIO;

import common.Constant;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * description:
 * create: 2018-08-04
 *
 * @author zhun.huang
 */
public class Client {

    public static void main(String[] args) throws IOException {
        String host = Constant.serverHost;
        int port = Constant.serverPort;
        SocketAddress socketAddress = new InetSocketAddress(host,port);
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
