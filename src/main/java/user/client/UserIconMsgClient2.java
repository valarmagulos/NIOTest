package user.client;

import common.Constant;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * description:
 * create: 2018-08-13
 *
 * @author zhun.huang
 */
public class UserIconMsgClient2 {

    public static void main(String[] args) throws Exception {
        EventLoopGroup workerGroup = new NioEventLoopGroup(5);

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(workerGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new UserIconMsgClientInitializer());

            Channel channel = bootstrap.connect(Constant.serverHost, Constant.serverPort).sync().channel();
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                channel.writeAndFlush(in.readLine());
            }
        } finally {
            workerGroup.shutdownGracefully();
        }
    }
}
