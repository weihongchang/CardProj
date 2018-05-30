package com.core.db;

import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.DB;
import com.mongodb.MongoClient;

import com.mongodb.client.MongoDatabase;

public class MongoHelper {

        static final String DBName = "CardGame";
        static final String ServerAddress = "127.0.0.1"; 
        static final int PORT = 27017;

        public MongoHelper(){
        }

        public MongoClient getMongoClient( ){
            MongoClient mongoClient = null;
            try {
                  // 连接到 mongodb 服务
                mongoClient = new MongoClient(ServerAddress, PORT); 
                System.out.println("Connect to mongodb successfully");
            } catch (Exception e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
            }
            return mongoClient;
        }

        public MongoDatabase getMongoDataBase(MongoClient mongoClient) {  
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
        
        public DB getMongoDB(MongoClient mongoClient) {  
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
        
        public MongoTemplate getMongoTemplate(MongoClient mongoClient)
        {
        	MongoTemplate mongoTemplate= null;
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
        
        

        public void closeMongoClient(MongoDatabase mongoDataBase,MongoClient mongoClient ) {  
            if (mongoDataBase != null) {  
                mongoDataBase = null;  
            }  
            if (mongoClient != null) {  
                mongoClient.close();  
            }  
            System.out.println("CloseMongoClient successfully");  

        }  
}