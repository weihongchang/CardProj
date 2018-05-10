package com.core;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class MessageEncoder extends MessageToByteEncoder<IMsg> {

	@Override
	protected void encode(ChannelHandlerContext ctx, IMsg msg, ByteBuf out) throws Exception {
		// TODO Auto-generated method stub
		ByteBuf buf = Unpooled.buffer();
		if (msg.write(buf)) {
			out.writeBytes(buf);
		}
		buf.release();
	}

}
