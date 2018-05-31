package com.core.db;

import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.WriteConcern;
import com.mongodb.MongoClientOptions.Builder;
import com.mongodb.client.MongoDatabase;

public class MongoManager {

        static final String DBName = "CardGame";
        static final String ServerAddress = "127.0.0.1"; 
        static final int PORT = 27017;
        
        private static MongoManager instance;
        
        private MongoClient mongoClient = null;
        MongoDatabase mongoDataBase = null;
        MongoTemplate mongoTemplate = null;
        
        public static MongoManager getInstance()
        {
        	if( instance == null )
        	{
        		instance = new MongoManager();
        	}
        	return instance;
        }

        public MongoManager(){
            getMongoClient();
            getMongoDataBase();
            getMongoTemplate();
        }

        public MongoClient getMongoClient( ){
           
            try {
                  // 连接到 mongodb 服务
                mongoClient = new MongoClient(ServerAddress, PORT); 
                
                // 大部分用户使用mongodb都在安全内网下，但如果将mongodb设为安全验证模式，就需要在客户端提供用户名和密码：
                // boolean auth = db.authenticate(myUserName, myPassword);
                Builder options = new MongoClientOptions.Builder();
                options.cursorFinalizerEnabled(true);
                // options.autoConnectRetry(true);// 自动重连true
                // options.maxAutoConnectRetryTime(10); // the maximum auto connect retry time
                options.connectionsPerHost(300);// 连接池设置为300个连接,默认为100
                options.connectTimeout(30000);// 连接超时，推荐>3000毫秒
                options.maxWaitTime(5000); //
                options.socketTimeout(0);// 套接字超时时间，0无限制
                options.threadsAllowedToBlockForConnectionMultiplier(5000);// 线程队列数，如果连接线程排满了队列就会抛出“Out of semaphores to get db”错误。
                options.writeConcern(WriteConcern.SAFE);//
                options.build();
                
                System.out.println("Connect to mongodb successfully");
            } catch (Exception e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
            }
            return mongoClient;
        }

        public MongoDatabase getMongoDataBase( ) {  
            MongoDatabase mongoDataBase = null;
            try {  
                if (mongoClient != null) {  
                      // 连接到数据库
                    mongoDataBase = mongoClient.getDatabase(DBName);  
                    System.out.println("Connect to DataBase successfully");
                } else {  
                    throw new RuntimeException("MongoClient不能够为空");  
                }  
            } catch (Exception e) {  
                e.printStackTrace();  
            }
            return mongoDataBase;
        }  
        
        public DB getMongoDB( ) {  
        	DB db = null;
            try {  
                if (mongoClient != null) {  
                      // 连接到数据库
                    db = mongoClient.getDB(DBName);  
                    System.out.println("Connect to DataBase successfully");
                } else {  
                    throw new RuntimeException("MongoClient不能够为空");  
                }  
            } catch (Exception e) {  
                e.printStackTrace();  
            }
            return db;
        }  
        
        public MongoTemplate getMongoTemplate( )
        {
        	
            try {  
                if (mongoClient != null) {  
                	mongoTemplate = new MongoTemplate(mongoClient, DBName);
                    System.out.println("Connect to DataBase successfully");
                } else {  
                    throw new RuntimeException("MongoClient不能够为空");  
                }  
            } catch (Exception e) {  
                e.printStackTrace();  
            }
            return mongoTemplate;
        }
        
        

        public void closeMongoClient(  ) {  
            if (mongoDataBase != null) {  
                mongoDataBase = null;  
            }  
            if (mongoClient != null) {  
                mongoClient.close();  
            }  
            System.out.println("CloseMongoClient successfully");  

        }  
}