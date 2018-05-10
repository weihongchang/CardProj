package tools.core;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;

public class FileUtils {

	private static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

	public static String loadFile(String filePath) throws IOException {
		BufferedReader in = getFileReader(filePath);
		StringBuilder buff = new StringBuilder();
		String line = null;
		while ((line = in.readLine()) != null) {
			buff.append(line);
			buff.append("\n");
		}
		in.close();
		return buff.toString();
	}

	public static void writeToFile(String content, String filePath) throws IOException {
		File file = new File(filePath);
		if (!file.exists()) {
			System.out.println(file.getAbsolutePath());
			file.createNewFile();
		}
		BufferedWriter out = new BufferedWriter(new FileWriter(filePath));
		out.write(content);
		out.close();
	}

	public static BufferedReader getFileReader(String filePath) throws IOException {
		return getFileReader(filePath, DEFAULT_CHARSET);
	}

	public static BufferedReader getFileReader(String filePath, Charset charset) throws IOException {
		return new BufferedReader(new InputStreamReader(new FileInputStream(filePath), charset));
	}

	public static URL getConfigURL(String fileName) {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		return classLoader.getResource(fileName);
	}

	public static String getConfigPath(String fileName) {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		return classLoader.getResource(fileName).getPath();
	}
}
