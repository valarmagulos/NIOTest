package Netty;

import common.Constant;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;

/**
 * description:
 * create: 2018-08-12
 *
 * @author zhun.huang
 */
public class MyFrameDecoder extends DelimiterBasedFrameDecoder {

    public MyFrameDecoder() {
        super(8192, Unpooled.copiedBuffer(Constant.endDelimeter.getBytes()));
    }
}