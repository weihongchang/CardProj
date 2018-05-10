package com.core;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.util.List;

import org.slf4j.Logger;

import com.core.Recognizer.GameMesageRecognizer;
import com.log.Loggers;

public class MessageDecoder extends ByteToMessageDecoder {
	private static final Logger logger = Loggers.gameLogger;

	private final GameMesageRecognizer recognizer;

	public MessageDecoder() {
		// TODO Auto-generated constructor stub
		recognizer = new GameMesageRecognizer();
	}

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in,
			List<Object> out) throws Exception {
		// TODO Auto-generated method stub
		logger.info("in decode!!!!");

		// 这个HEAD_LENGTH是我们用于表示头长度的字节数。 由于上面我们传的是一个int类型的值，所以这里HEAD_LENGTH的值为4.
		if (in.readableBytes() < IMsg.MIN_MESSAGE_LENGTH) {
			logger.info("HEAD_LENGTH is error!");
			return;
		}

		// //////////test begin/////////////////////
//		int dataLength = in.readInt();
//
//		if (dataLength < 0) {
//			ctx.close();
//		}
//		in.resetReaderIndex();
//
//		if (in.readableBytes() < dataLength) {
//			in.resetReaderIndex();
//			return;
//		}
//
//		byte[] body = new byte[dataLength];
//		in.readBytes(body);
//
//		DataInputStream dis = new DataInputStream(
//				new ByteArrayInputStream(body));
//		int msglen = dis.readShort();
//		int msgtype = dis.readShort();
//		System.out.println("msg ");
		// //////////test end/////////////////////

		int length = recognizer.recognizePacketLen(in);
		if (length < IMsg.HEADER_SIZE) {
			// 长度小于消息头长度直接退出
			ctx.close();
		}

		if (in.readableBytes() < length) {
			in.resetReaderIndex();
			return;
		}

		IMsg msg = recognizer.recognize(in);
		if (msg == null) {
			return;
		}

//		if (msg.read(in)) {
//			in.clear();
//			out.add(msg);
//		}

	}

}
