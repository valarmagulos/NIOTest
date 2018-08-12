package BIO;

import common.Constant;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * description:
 * create: 2018-08-12
 *
 * @author zhun.huang
 */
public class Server {
    static ExecutorService timeHandlerExecutor = Executors.newFixedThreadPool(5);

    static boolean shutDown = false;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(Constant.serverPort);
        System.out.println("服务器启动...");

        while (!shutDown) {
            Socket accept = serverSocket.accept();
            System.out.println("收到一次客户端请求" + accept.hashCode());
            timeHandlerExecutor.execute(new TimeHandler(accept));
        }
        System.out.println("服务器停止...");
    }

    public static class TimeHandler implements Runnable {

        private final Socket socket;

        TimeHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                String requestStr = MyProtocol.read(socket);
                if (Constant.TimeAction.equals(requestStr)) {
                    System.out.println("响应服务器时间");
                    MyProtocol.write(socket, String.valueOf(System.currentTimeMillis()));
                } else {
                    MyProtocol.write(socket, "不识别的客户端指令");
                    System.err.println("不识别的客户端指令");
                }
            } catch (Exception e) {
                System.err.println("获取输入信息异常, errMsg:" + e.getMessage());
            }
        }
    }
}
