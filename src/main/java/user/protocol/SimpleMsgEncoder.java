package user.protocol;

import common.Constant;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

/**
 * description:
 * create: 2018-08-21
 *
 * @author zhun.huang
 */
public class SimpleMsgEncoder extends MessageToMessageEncoder<CharSequence> {

    @Override
    protected void encode(ChannelHandlerContext ctx, CharSequence msg, List<Object> out) throws Exception {
        if (msg.length() != 0) {
            out.add(msg + Constant.endDelimeter);
        }
    }
}
