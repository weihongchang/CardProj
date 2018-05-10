package tools.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class ProtoComplier {

	private boolean error = false;
	
	public ProtoComplier() {
	}
	
	/**
	 * 
	 * @param messageDirPath 协议文件.proto所在目录(用于寻找import文件等)
	 * @param javaOutPath 目标文件.java 输出目录
	 * @param fileName 要编译的协议文件名
	 */
	public void complier(String messageDirPath, String javaOutPath, String fileName) {
		System.out.println("开始编译[" + fileName + "]...");
		Runtime runtime = Runtime.getRuntime();
		Process p;
		try {
			p = runtime.exec(messageDirPath+"/protoc -I=" + messageDirPath + " --java_out=" + javaOutPath + " " + messageDirPath + "/" + fileName);
			BufferedReader buff = new BufferedReader(new InputStreamReader(p.getInputStream(), Charset.forName("GBK")));
			String line = null;
			while ((line = buff.readLine()) != null) {
				System.out.println(line);
			}
			
			buff = new BufferedReader(new InputStreamReader(p.getErrorStream(), Charset.forName("GBK")));
			while ((line = buff.readLine()) != null) {
				System.err.println(line);
				error = true;
			}
			p.destroy();
			if (error) {
				System.err.println("编译[" + fileName + "]失败");
				System.exit(-1);
			}
		} catch (IOException e) {
			System.err.println("编译[" + fileName + "]失败");
			e.printStackTrace();
			System.exit(-1);
		}
		System.out.println("编译[" + fileName + "]成功, 已输出到目录[" + new File(javaOutPath).getAbsolutePath() + "]");
	}
	
	public boolean hasError() {
		return error;
	}
}
