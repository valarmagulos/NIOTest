package Netty;

import common.Constant;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * description:
 * create: 2018-08-12
 *
 * @author zhun.huang
 */
public class ServerHandler extends SimpleChannelInboundHandler<String> {
    private static final Logger LOGGER = LoggerFactory.getLogger(ServerHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String body) throws Exception {
        System.out.println("收到客户端请求为:" + body);
        if (Constant.TimeAction.equals(body)) {
            System.out.println("async响应服务器时间");
            String response = String.valueOf(System.currentTimeMillis());
            ctx.channel().writeAndFlush(response + Constant.endDelimeter);
        } else {
            ctx.channel().writeAndFlush("不识别的客户端指令" + body + Constant.endDelimeter);

            System.err.println("不识别的客户端指令:" + body);
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        LOGGER.error("server exceptionCaught:", cause);
    }
}
