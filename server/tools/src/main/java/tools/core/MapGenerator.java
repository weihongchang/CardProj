package tools.core;

import java.io.IOException;
import java.util.List;

/**
 * 消息映射生成�?
 */
public class MapGenerator {

    private static final String TEMPLATE_PATH = "./config/protobuf/mapTemplate.txt";

    private static final String INIT_PREFIX1 = "\t\tidClazzMap.put(";
    private static final String INIT_MIDDLE1 = ", ";
    private static final String INIT_SUFFIX1 = ".getDefaultInstance());\n";

    private static final String INIT_PREFIX2 = "\t\tclazzIdMap.put(";
    private static final String INIT_MIDDLE2 = ".class,";
    private static final String INIT_SUFFIX2 = ");\n";

    private static final String INIT_PREFIX3 = "\t\tmap.put((short)";
    private static final String INIT_MIDDLE3 = ".class";
    private static final String INIT_SUFFIX3 = ");\n";

    private String targetFilePath;

    public MapGenerator(String targetFilePath) {
        this.targetFilePath = targetFilePath;
    }

    public void generate(List<MessageInfo> list) {
        System.out.println("始生成MessageMap代码...");
        String template = loadTemplate();
        try {
            createTargets(list, template);
        } catch (IOException e) {
            System.err.println("生成MessageMap代码失败...");
            e.printStackTrace();
            System.exit(-1);
        }
        System.out.println("生成MessageMap代码完毕...");
    }

    private void createTargets(List<MessageInfo> list, String template)
            throws IOException {
        StringBuilder initBuff = new StringBuilder();
        for (MessageInfo info : list) {
//            initBuff.append(INIT_PREFIX1).append(info.getMessageId())
//                    .append(INIT_MIDDLE1).append(info.getMessageName())
//                    .append(INIT_SUFFIX1);
            if( info.getMessageId()%2 != 0 ) 
            {
                initBuff.append(INIT_PREFIX3).append(info.getMessageId())
                        .append(",").append(info.getMessageName()).append("Handler")
                        .append(INIT_MIDDLE3)
                        .append(INIT_SUFFIX3);
            }
            else
            {
            	initBuff.append(INIT_PREFIX3).append(info.getMessageId())
                .append(",").append("Message.").append(info.getMessageName())
                .append(INIT_MIDDLE3)
                .append(INIT_SUFFIX3);
            }
        }
        String content = template.replaceAll("#initCode#", initBuff.toString());
        FileUtils.writeToFile(content, targetFilePath);

    }

    private String loadTemplate() {
        String buff = null;
        try {
            buff = FileUtils.loadFile(TEMPLATE_PATH);
        } catch (IOException e) {
            System.err.println("加载模版文件失败...");
            e.printStackTrace();
            System.exit(-1);
        }
        return buff;
    }
}
