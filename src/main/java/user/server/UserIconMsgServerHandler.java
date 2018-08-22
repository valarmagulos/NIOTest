package user.server;

import com.google.common.base.Stopwatch;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import user.server.service.UserIconLocalCacheService;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * description:
 * create: 2018-08-21
 *
 * @author zhun.huang
 */
public class UserIconMsgServerHandler extends SimpleChannelInboundHandler{

    private static AtomicLong serverCount = new AtomicLong(0);

    private static Stopwatch stopwatch = Stopwatch.createUnstarted();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object o) throws Exception {
        if (serverCount.longValue() == 0) {
            stopwatch.start();
        }
        String key = o.toString();
        final String userIconUrl = UserIconLocalCacheService.queryByKey(key);
        ctx.channel().writeAndFlush(userIconUrl);
        final long count = serverCount.addAndGet(1);
        System.out.println("当前qps:" + count / stopwatch.elapsed(TimeUnit.SECONDS));
    }
}
