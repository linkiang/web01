package com.lq.web01.platform.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.util.Assert;

import com.lq.web01.platform.log.LQLogger;
import com.lq.web01.platform.log.LQLoggerFactory;

public class FileUtils {
	private static final LQLogger LOG = LQLoggerFactory
			.getLogger(FileUtils.class);

	private FileUtils() {
	};

	private static String basePath = System.getProperty("user.dir").replace(
			"\\", "/");

	public static byte[] readAll(InputStream in) {
		LOG.info("fileutil basePath: " + basePath);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			byte[] buffer = new byte[1024];
			for (int n; (n = in.read(buffer)) > 0;) {
				out.write(buffer, 0, n);
			}
		} catch (Exception e) {
			LOG.error("读取文件失败", e);
		}
		return out.toByteArray();
	}

	public static boolean removeFile(String path) {
		try {
			File file = new File(getAbsolutePath(path));
			if (file.exists()) {
				if (!file.delete()) {
					file.deleteOnExit();
				}
				return true;
			}
		} catch (Exception e) {
			LOG.error("文件操作失败", e);
		}
		return false;
	}

	public static String getAbsolutePath(String path) {
		Assert.notNull(path);
		// 在windows下，如果路径包含：,为绝对路径，则不进行转换
		if (path.contains(":")) {
			return path.replace("\\", "/");
		}
		LOG.debug("转换路径：" + path);
		path = path.replace("\\", "/").trim();
		path = basePath + "/" + path;
		while (path.contains("//")) {
			path = path.replace("//", "/");
		}
		LOG.debug("返回路径：" + path);
		return path;
	}

	public static File createAndWriteFile(String path, String text) {
		try {
			File file = new File(getAbsolutePath(path));
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			file.createNewFile();
			try (BufferedWriter writer = new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream(file), "utf-8"))) {
				writer.write(text);
			}
			return file;
		} catch (Exception e) {
			LOG.error("文件操作失败", e);
		}
		return null;
	}

	public static List<String> getTextFileContent(String path) {
		try {
			return getTextFileContent(new FileInputStream(getAbsolutePath(path)));
		} catch (Exception e) {
			if (!path.contains("apdplat.licence")) {
				LOG.error("文件不存在", e);
			}
			return Collections.emptyList();
		}
	}

	private static List<String> getTextFileContent(
			FileInputStream fileInputStream) {
		List<String> result = new ArrayList<>();
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(
				fileInputStream, "utf-8"))) {
			for (;;) {
				String line = reader.readLine();
				if (line == null) {
					break;
				}
				result.add(line);
			}
		} catch (Exception e) {
			LOG.error("读取文件失败", e);
		}
		return result;
	}

}
