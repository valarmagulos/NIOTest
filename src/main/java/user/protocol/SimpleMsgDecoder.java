package user.protocol;

import common.Constant;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;

/**
 * description:
 * create: 2018-08-21
 *
 * @author zhun.huang
 */
public class SimpleMsgDecoder extends DelimiterBasedFrameDecoder {

    private static int maxFrameLength = 1024;
    private static ByteBuf delimiter = Unpooled.copiedBuffer(Constant.endDelimeter.getBytes());

    public SimpleMsgDecoder() {
        super(maxFrameLength, delimiter);
    }
}
