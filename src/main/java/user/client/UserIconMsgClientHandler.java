package user.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Random;

/**
 * description:
 * create: 2018-08-21
 *
 * @author zhun.huang
 */
public class UserIconMsgClientHandler extends SimpleChannelInboundHandler{

    private Random random = new Random();
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object o) throws Exception {
        System.out.println("收到客户端请求为:" + o.toString());
        ctx.channel().writeAndFlush(String.valueOf(random.nextInt()));
    }
}
