package tools.core;


import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author weihongchang
 * @date 2015年11月19日
 */
public class MsgAndMapGen2015 {

    private static final String PROJECT_PATH = "./";
//    private static final String MESSAGE_MAP_PATH = PROJECT_PATH + "/config/java/com/wanmei/project612/network/protocol/generate/PlayerMsgMappingProvider.java";
    private static final String MESSAGE_MAP_PATH = PROJECT_PATH + "/config/java/PlayerMsgMappingProvider.java";
    private static final String MESSAGE_PATH = "config/protobuf/message.proto";

    public static void main(String[] args) {
        // 生成消息map
        MapGenerator g = new MapGenerator(MESSAGE_MAP_PATH);

        List<MessageInfo> smList = ProtoFileParser.getAllMessageInfo(MESSAGE_PATH, false);
//        List<MessageInfo> scList = ProtoFileParser.getAllMessageInfo(MESSAGE_PATH, false);
        List<MessageInfo> allList = new ArrayList<MessageInfo>();
//        allList.addAll(scList);
        allList.addAll(smList);

        g.generate(allList);

        // 生成Server<->Client消息
        new ScMsgGenerator(PROJECT_PATH).generate();

    }
}
