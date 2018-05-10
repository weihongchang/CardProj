package tools.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProtoFileParser {

	private static final Pattern MESSAGE_ID_PATTERN = Pattern.compile("\\s*//\\s*messageID\\s*[:：]\\s*(\\d+).*", Pattern.CASE_INSENSITIVE);
	
	private static final Pattern MESSAGE_PATTERN = Pattern.compile("\\s*message\\s+(\\w+)\\s*\\{*\\s*");
	
	private static final Charset CHARSET = Charset.forName("UTF-8");

	public static BufferedReader loadProtoFile(String file) {
		BufferedReader buff = null;
		try {
			buff = FileUtils.getFileReader(file, CHARSET);
		} catch (IOException e) {
			System.err.println("加载protobuf配置文件失败...");
			e.printStackTrace();
			System.exit(-1);
		}
		return buff;
	}

	public static List<MessageInfo> getAllMessageInfo(String file, boolean endWithReqResp) {
		List<MessageInfo> list = new ArrayList<MessageInfo>();
		BufferedReader in = loadProtoFile(file);
		Set<Integer> idSet = new HashSet<Integer>();
		Set<String> omitedMessages = new HashSet<String>();
		String line = null;
		try {
			while ((line = in.readLine()) != null) {
				// 如果出现直接匹配消息的  说明是没有分配ID
				Matcher m = MESSAGE_PATTERN.matcher(line);
				if (m.matches()) {
					omitedMessages.add(m.group(1));
				}
				// 先匹配ID
				Matcher matcher = MESSAGE_ID_PATTERN.matcher(line);
				if (matcher.matches()) {
					int messageId = Integer.parseInt(matcher.group(1));
					// 匹配到ID 按规则下一行就是消息
					String nextLine = in.readLine();
					Matcher messageMatcher = MESSAGE_PATTERN.matcher(nextLine);
					String messageName = null;
					if (messageMatcher.matches()) {
						messageName = messageMatcher.group(1);
					} else {
						System.err.println(line + " 的下一行不是一个消息message!!");
						System.exit(-1);
					}
					if (idSet.contains(messageId)) {
						System.err.println("messageId:" + messageId + " 出现重复！");
						System.exit(-1);
					} else {
						idSet.add(messageId);
					}
					MessageInfo messageInfo = new MessageInfo();
					messageInfo.setMessageId(messageId);
					messageInfo.setMessageName(messageName);
					if (messageName.endsWith("Req")) {
						messageInfo.setType(MessageType.REQUEST);
						if (!isOdd(messageId)) {
							System.err.println("Warning:消息" + messageInfo.getMessageName() + " 的ID不是奇数!");
						}
					} else if (messageName.endsWith("Resp")) {
						messageInfo.setType(MessageType.RESPONSE);
						if (isOdd(messageId)) {
							System.err.println("Warning:消息" + messageInfo.getMessageName() + " 的ID不是偶数!");
						}
					} else {
						if (endWithReqResp) {
							messageInfo.setType(MessageType.REQUEST);
							System.err.println("Warning:消息" + messageInfo.getMessageName() + " 既不是Req结尾也不是Resp结尾，生成文件可能有误!");
						}
					}
					list.add(messageInfo);
				}
			}
			in.close();
		} catch (IOException e) {
			System.err.println("解析protobuf配置文件出错...");
			e.printStackTrace();
			System.exit(-1);
		}
		if (omitedMessages.size() > 0) {
			System.err.println("有消息没有分配ID！！");
			System.out.println(omitedMessages);
			System.exit(-1);
		}
		System.out.println("[" + file + "]一共有如下[" + list.size() + "]个消息：");
		for (MessageInfo messageInfo : list) {
			System.out.println(messageInfo.getMessageName() + " : " + messageInfo.getMessageId());
		}
		return list;
	}
	
	public static boolean isOdd(int n) {
		return n % 2 != 0;
	}
	
	public static void main(String[] args) {
	}
}
