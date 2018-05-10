package com.core;


import java.net.InetSocketAddress;
import java.nio.ByteBuffer;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import org.slf4j.Logger;

import com.core.Exception.MessageParseException;
import com.core.Recognizer.GameMesageRecognizer;
import com.fasterxml.jackson.databind.util.ByteBufferBackedInputStream;
import com.log.Loggers;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;

public class WsServer extends WebSocketServer {
	private static final Logger logger = Loggers.gameLogger;
	
	private final GameMesageRecognizer recognizer;
	
    public WsServer(int port) {
        super(new InetSocketAddress(port));
        recognizer = new GameMesageRecognizer();
    }

    public WsServer(InetSocketAddress address) {
        super(address);
        recognizer = new GameMesageRecognizer();
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        // ws连接的时候触发的代码，onOpen中我们不做任何操作
    	System.out.println("新链接"+conn.getRemoteSocketAddress());
    	WsPool.addUser(conn.getRemoteSocketAddress().toString(), conn);
    	
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        //断开连接时候触发代码
    	System.out.println("断开连接");
        userLeave(conn);
        System.out.println(reason);
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        System.out.println(message);
        if(null != message &&message.startsWith("online")){
            String userName=message.replaceFirst("online", message);//用户名
            userJoin(conn,userName);//用户加入
        }else if(null != message && message.startsWith("offline")){
            userLeave(conn);
        }

    }
    
    @Override
    public void onMessage(WebSocket conn, ByteBuffer message) {
    	// TODO Auto-generated method stub
    	super.onMessage(conn, message);
    	System.out.println(conn.getRemoteSocketAddress()+"    "+message.toString());
		logger.info("in decode!!!!");
		try {
		
			//1、创建缓冲区  
			ByteBuf in = Unpooled.buffer(message.array().length);
			in.writeBytes(message.array());
			
			// 这个HEAD_LENGTH是我们用于表示头长度的字节数。 由于上面我们传的是一个int类型的值，所以这里HEAD_LENGTH的值为4.
			if (in.readableBytes() < IMsg.MIN_MESSAGE_LENGTH) {
				logger.info("HEAD_LENGTH is error!");
				return;
			}
			int length = recognizer.recognizePacketLen(in);
			if (length < IMsg.HEADER_SIZE) {
				// 长度小于消息头长度直接退出
//				ctx.close();
			}

			if (in.readableBytes() < length) {
				in.resetReaderIndex();
				return;
			}

			IMsg msg = recognizer.recognize(in);
			if (msg == null) {
				return;
			}
			msg.setConn(conn);
			
			if (msg.read(in,conn)) {
				in.clear();
				msg.execute();
			}
		} catch (MessageParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        //错误时候触发的代码
        System.out.println("on error");
        ex.printStackTrace();
    }
    /**
     * 去除掉失效的websocket链接
     * @param conn
     */
    private void userLeave(WebSocket conn){
        WsPool.removeUser(conn);
    }
    /**
     * 将websocket加入用户池
     * @param conn
     * @param userName
     */
    private void userJoin(WebSocket conn,String userName){
        WsPool.addUser(userName, conn);
    }

}