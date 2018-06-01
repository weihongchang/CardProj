package com.core;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.java_websocket.WebSocket;
import org.slf4j.Logger;

import com.core.Exception.MessageParseException;
import com.core.Message.List.SCAllMessageHandler;
import com.google.protobuf.GeneratedMessage;
import com.log.Loggers;
import com.model.player.Player;
import com.start.GameStart;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class WsPool {
	
    private static final Map<WebSocket, String> wsUserMap = new HashMap<WebSocket, String>();
    private static final Map<String, Player> wsPlayerMap = new HashMap<String, Player>();
    private static final Logger logger = Loggers.gameLogger;
    /**
     * 通过websocket连接获取其对应的用户
     * 
     * @param conn
     * @return
     */
    public static String getUserByWs(WebSocket conn) {
        return wsUserMap.get(conn);
    }

    /**
     * 根据userName获取WebSocket,这是一个list,此处取第一个
     * 因为有可能多个websocket对应一个userName（但一般是只有一个，因为在close方法中，我们将失效的websocket连接去除了）
     * 
     * @param user
     */
    public static WebSocket getConnByUser(String userName) {
        Set<WebSocket> keySet = wsUserMap.keySet();
        synchronized (keySet) {
            for (WebSocket conn : keySet) {
                String cuser = wsUserMap.get(conn);
                if (cuser.equals(userName)) {
                    return conn;
                }
            }
        }
        return null;
    }
    

    
    public static Player getPlayerByUser(String userName)
    {
    	if(wsPlayerMap.containsKey(userName))
    	{
    		return wsPlayerMap.get(userName);
    	}
    	
        return null;
    }
    
    public static void updatePlayer(Player player,WebSocket conn)
    {
    	wsPlayerMap.put(conn.getRemoteSocketAddress().toString(), player);
    }

    /**
     * 向连接池中添加连接
     * 
     * @param inbound
     */
    public static void addUser(String userName, WebSocket conn) {
        wsUserMap.put(conn, userName); // 添加连接
        wsPlayerMap.put(userName, new Player(userName));
        System.out.println("新用户["+userName+"]上线");
    }

    /**
     * 获取所有连接池中的用户，因为set是不允许重复的，所以可以得到无重复的user数组
     * 
     * @return
     */
    public static Collection<String> getOnlineUser() {
        List<String> setUsers = new ArrayList<String>();
        Collection<String> setUser = wsUserMap.values();
        for (String u : setUser) {
            setUsers.add(u);
        }
        return setUsers;
    }

    /**
     * 移除连接池中的连接
     * 
     * @param inbound
     */
    public static boolean removeUser(WebSocket conn) {
        if (wsUserMap.containsKey(conn)) {
        	System.out.println("新用户["+wsUserMap.get(conn)+"]下线");
        	wsPlayerMap.remove(wsUserMap.get(conn));
            wsUserMap.remove(conn); // 移除连接
            return true;
        } else {
            return false;
        }
    }

    /**
     * 向特定的用户发送数据
     * 
     * @param user
     * @param message
     */
    public static void sendMessageToUser(WebSocket conn, String message) {
        if (null != conn && null != wsUserMap.get(conn)) {
            conn.send(message);
        }
    }
    
    /**
     * 向特定的用户发送数据
     * 
     * @param user
     * @param message
     * @throws MessageParseException 
     */
    public static void sendMessageToUser(WebSocket conn, GeneratedMessage msgLite) {
    	try {
	        if (null != conn && null != wsUserMap.get(conn)) {
	//            conn.send(messageLit);
	        	SCAllMessageHandler msg = new SCAllMessageHandler(msgLite);
	    		short mType = GameStart.recognizer.getTypeForMsg(msgLite.getClass());
	    		msg.setType(mType);
	    		
	    		ByteBuf buf = Unpooled.buffer();
	    		msg.write(buf);
	    		int dataLength = buf.readableBytes();
				byte[] body = new byte[dataLength];
				buf.readBytes(body);
	    		conn.send(body);
	    		
				logger.info("[send] " + msg.toString());
				logger.debug("[send] " + msg.toString());
	    		
	        }
    	}
    	catch(Exception e)
    	{
    		logger.error(e.getMessage());
    	}
    }

    /**
     * 向所有的用户发送消息
     * 
     * @param message
     */
    public static void sendMessageToAll(String message) {
        Set<WebSocket> keySet = wsUserMap.keySet();
        synchronized (keySet) {
            for (WebSocket conn : keySet) {
                String user = wsUserMap.get(conn);
                if (user != null) {
                    conn.send(message);
                }
            }
        }
    }

}