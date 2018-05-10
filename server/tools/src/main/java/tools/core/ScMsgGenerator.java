package tools.core;

/**
 * Server<-->Client 协议消息生成器
 */
public class ScMsgGenerator {

    private static final String JAVA_OUT_DIR = "/config/java/";

    public static final String messageSourceDir = "./config/protobuf/";

    private String projectPath;

    public ScMsgGenerator(String projectPath) {
        this.projectPath = projectPath;
    }

    /**
     * 生成协议消息
     */
    public void generate() {
        ProtoComplier protoComplier = new ProtoComplier();
        String javaOutDir = projectPath + JAVA_OUT_DIR;
        protoComplier.complier(messageSourceDir, javaOutDir, "enum.proto");
        protoComplier.complier(messageSourceDir, javaOutDir, "struct.proto");
        protoComplier.complier(messageSourceDir, javaOutDir, "message.proto");
    }

}
