package user.client;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import user.protocol.SimpleMsgDecoder;
import user.protocol.SimpleMsgEncoder;
import user.server.UserIconMsgServerHandler;

/**
 * description:
 * create: 2018-08-21
 *
 * @author zhun.huang
 */
public class UserIconMsgClientInitializer extends ChannelInitializer {
    @Override
    protected void initChannel(Channel channel) throws Exception {
        final ChannelPipeline pipeline = channel.pipeline();
        pipeline.addLast(new StringEncoder());
        pipeline.addLast(new StringDecoder());
        pipeline.addLast(new SimpleMsgEncoder());
        pipeline.addLast(new SimpleMsgDecoder());
        pipeline.addLast(new UserIconMsgClientHandler());
    }

}
