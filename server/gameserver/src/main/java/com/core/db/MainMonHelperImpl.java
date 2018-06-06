package com.core.db;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.model.player.Player;

/**
 * 测试类
 * @author koo
 *
 */
public class MainMonHelperImpl { 
	
    public static void main(String[] args) throws Exception {
//    	
//    	MongoHelper mongoHelper = new MongoHelper();
//        MongoClient mongoClient = mongoHelper.getMongoClient();
//        MongoDatabase mongoDataBase = mongoHelper.getMongoDataBase(mongoClient);
//        MongoTemplate mongoTemplate = mongoHelper.getMongoTemplate(mongoClient);
//        MongoDaoImpl mongoDaoImpl = new MongoDaoImpl();
        
//		增加一条数据
//        User u1=new User();
//        u1.setId(1+"");
//        u1.setContent("ceshi1");
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
//        u1.setDate(df.format(new Date()));
//        u1.setTitle("title1");        
//        
//        mongoTemplate.insert(u1);

//        
//        //查询数据
//        List<User> userList =  mongoTemplate.findAll(User.class);
//        if(userList != null)
//        {
//        	for (Iterator iterator = userList.iterator(); iterator.hasNext();) {
//				User user = (User) iterator.next();
//				if( user != null )
//				{
//					System.out.println(user.getId()+"   "+user.getContent()+"   "+user.getDate());
//				}
//			}
//        }
//        else
//        {
//        	System.out.println("list is null!!!");
//        }
        
		User u1=new User();
		u1.setId(1+"");
		u1.setContent("ceshi1");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		u1.setDate(df.format(new Date()));
		u1.setTitle("title1");    
        
		MongoManager.getInstance().getMongoTemplate().save(u1);
        //修改数据
//        Query query=new Query(Criteria.where("account").is("用户名"));
//        Update update = Update.update("level", 5);
//        MongoManager.getInstance().getMongoTemplate().updateFirst(query, update, Player.class);
        
//        Map<String,Integer> map1 = mongoDaoImpl.queryByID(mongoDataBase, table, 2);//检索event_id,注意id类型是字符串还是int
//        if(map1 != null)
//        {
//	        for (Map.Entry entry : map1.entrySet()) {  
//	        	  
//	            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());  
//	          
//	        }
//        }
//        else
//        {
//        	System.out.println("map is null!!!");
//        }
//      BasicDBObject document2 = new BasicDBObject("likes",1000);
//      document2.append("event_id", "55");
//      mongoDaoImpl.queryByDoc(mongoDataBase, table, document2);//检索doc,可以根据doc(key,value)来查找,当doc是空的时候，检索全部
//      mongoDaoImpl.queryAll(mongoDataBase, table); //查询全部	

//      BasicDBObject document3 = new BasicDBObject("likes",200);
//      mongoDaoImpl.delete(mongoDataBase, table, document3);//删除doc 的全部信息，当doc 是空，则删除全部
//      BasicDBObject document3 = new BasicDBObject("likes", 1000);
//      mongoDaoImpl.deleteOne(mongoDataBase, table, document3);//删除doc 的一个信息

//      更新文档   将文档中likes=100的文档修改为likes=200   
//      BasicDBObject whereDoc = new BasicDBObject("likes",1000);
//      BasicDBObject updateDoc = new BasicDBObject("likes",255);
//      mongoDaoImpl.update(mongoDataBase, table, whereDoc, updateDoc);//更新全部,查找到oldDoc的数据，更新newDoc的数据
//      BasicDBObject whereDoc = new BasicDBObject("likes",255);
//      BasicDBObject updateDoc = new BasicDBObject("event_id",205);
//      mongoDaoImpl.updateOne(mongoDataBase, table, whereDoc, updateDoc);//更新全部,查找到oldDoc的数据，更新newDoc的数据


//------------------------------------------------
        /**
         * 使用map 进行CRUD操作
         */

//      System.out.println("测试map");
//      Map<String, Object> areaMap1 = new HashMap<String,Object>();
//      areaMap1.put("_id", 2);
//      areaMap1.put("北京", 5);
//      areaMap1.put("上海", 14);
//      areaMap1.put("广州",8);
//      areaMap1.put("深圳",0.5);


         //把map反序列化
//      System.out.println("map parse: " + JSON.parse(JSON.serialize(areaMap)));  

        // 根据map 到mongodb查询
//     FindIterable<Document> queryByDocResult = mongoDaoImpl.queryByDoc(mongoDataBase, areaTable, new BasicDBObject(areaMap1));
//     mongoDaoImpl.printFindIterable(queryByDocResult);


        //   插入map 到mongodb
//      mongoDaoImpl.insert(mongoDataBase, areaTable, new Document(areaMap1));

//      Map<String, Object> areaMap2 = new HashMap<String,Object>();
//      Map<String, Object> areaMap3 = new HashMap<String,Object>();
//      areaMap2.put("_id", 10);
//      areaMap2.put("北京", 5);
//
//      areaMap3.put("_id", 11);
//      areaMap3.put("北京", 5);
//      List<Document> docList = new ArrayList<Document>();
//      docList.add(new Document(areaMap2));
//      docList.add(new Document(areaMap3));
//      mongoDaoImpl.insertMany(mongoDataBase, areaTable, docList);

        //   根据map 删除mongodb
//      mongoDaoImpl.delete(mongoDataBase, areaTable, new BasicDBObject(areaMap1));
//      mongoDaoImpl.deleteOne(mongoDataBase, areaTable, new BasicDBObject(areaMap1));

        //根据map 更新mongodb
//      Map<String, Object> updateDoc = new HashMap<String,Object>();
//      Map<String, Object> wehereDoc = new HashMap<String,Object>();
//      wehereDoc.put("_id", 4);
//      updateDoc.put("上海",25);
//      mongoDaoImpl.update(mongoDataBase, areaTable, new BasicDBObject(wehereDoc), new BasicDBObject(updateDoc));
//      mongoDaoImpl.updateOne(mongoDataBase, areaTable, new BasicDBObject(wehereDoc), new BasicDBObject(updateDoc));


        //检索全部
//      FindIterable<Document> queryAllResult = mongoDaoImpl.queryAll(mongoDataBase, table);
//      mongoDaoImpl.printFindIterable(queryAllResult);
        
        MongoManager.getInstance().closeMongoClient();

    }

}