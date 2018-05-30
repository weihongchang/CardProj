package com.start;

import org.java_websocket.WebSocketImpl;
import org.slf4j.Logger;

import com.core.WsServer;
import com.core.Recognizer.GameMesageRecognizer;
import com.core.db.DbManager;
import com.core.db.MongoUtil;
import com.log.Loggers;
import com.mongodb.client.MongoDatabase;


public class GameStart {
	private static final Logger logger = Loggers.gameLogger;
	public static GameMesageRecognizer recognizer;

	public static void main(String[] args)
	{
		logger.info("Game Start .....");
		// 获取当前时间
		long t0 = System.currentTimeMillis();
//		DbManager.getSession();
		MongoDatabase md = MongoUtil.instance.getDB("CardGame");
		if(md == null)
		{
//			MongoUtil.instance.
		}
//		ChannelInboundHandler<GameMessageChannel> ioHandler = new GameChannelInboundHandler();
		
//		IMsgDispatcher msgDispatcher = new GameMsgDispatcher<IMsgProcessor>(MainMsgProcessor.theInstance());
		
//		GameMesageRecognizer recognizer = new GameMesageRecognizer();
		recognizer = new GameMesageRecognizer();
		
//		ServerProcess process = new ServerProcess(ioHandler,msgDispatcher,recognizer);
		
//		process.start();
		
		WebSocketImpl.DEBUG = false;
        int port = 8888; // 端口
        WsServer s = new WsServer(port);
        s.start();
		
		logger.info("Game Server started");

		long t1 = System.currentTimeMillis();

		logger.info("服务器启动时间 : " + (t1 - t0) + "毫秒");
	}
	

}
