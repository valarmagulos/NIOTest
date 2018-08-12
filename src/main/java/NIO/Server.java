package NIO;

import common.Constant;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
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
        SocketAddress socketAddress = new InetSocketAddress(Constant.serverHost, Constant.serverPort);
        Selector selector = Selector.open();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.socket().bind(socketAddress);

        // 注册到selector上．
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        System.out.println("服务器已启动...");

        while (!shutDown) {
            selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                iterator.remove();
                if (!selectionKey.isValid()) {
                    System.err.println("该连接已经无效");
                    continue;
                }
                if (selectionKey.isAcceptable()) {
                    ServerSocketChannel serverSocketChannel1 = (ServerSocketChannel) selectionKey.channel();
                    SocketChannel socketChannel = serverSocketChannel1.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ);
                }
                if (selectionKey.isReadable()) {
                    SocketChannel sc = (SocketChannel) selectionKey.channel();
                    System.err.println("当前socket:" + sc.hashCode());
                    timeHandlerExecutor.execute(new timeHandler(sc));
                }
            }
        }
        System.out.println("服务器已停止...");
    }

    public static class timeHandler implements Runnable {

        private SocketChannel sc;

        public timeHandler(SocketChannel sc) {
            this.sc = sc;
        }

        @Override
        public void run() {
            try {
                String requestStr = MyProtocol.read(sc);
                if (Constant.TimeAction.equals(requestStr)) {
                    System.out.println("async响应服务器时间");
                    MyProtocol.write(sc, String.valueOf(System.currentTimeMillis()));
                } else {
                    MyProtocol.write(sc, "不识别的客户端指令");
                    System.err.println("不识别的客户端指令");
                }
            } catch (Exception e) {
                System.err.println("获取输入信息异常, errMsg:" + e.getMessage());
            } finally {
                try {
                    sc.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
