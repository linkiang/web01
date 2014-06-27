package web01;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.charset.Charset;

import org.junit.Test;

import com.lq.web01.module.security.service.SecurityCheck;

public class Test01 {

	@Test
	public void test01() {
		SecurityCheck.check();
	}

	// 测试非静态内部类
	@Test
	public void test02() {
		ClassOuter out = new ClassOuter();
		ClassOuter.ClassInner inner = out.new ClassInner();
		inner.method1();
	}

	@Test
	public void test03() throws IOException {

		InputStreamReader inputStreamReader = new InputStreamReader(
				new FileInputStream("D:/uni.txt"), "unicode");

		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		while (bufferedReader.ready()) {
			String s = bufferedReader.readLine();
			System.out.println(s);
			byte c[] = s.getBytes("utf-8");
			String s1 = new String(c, "utf-8");
			System.out.println(s1);
		}
		bufferedReader.close();
		//
		// FileInputStream fileInputStream = new FileInputStream("D:/asci.txt");
		// byte[] b = new byte[1024];
		// fileInputStream.read(b);
		// String s=new String(b, "utf-8");
		// System.out.println(s);
		// fileInputStream.close();
		// System.out.println(Charset.defaultCharset());
	}

	@Test
	public void test04() throws UnsupportedEncodingException {
		String s = "中国"; // unicode
		byte[] b = s.getBytes("utf-8");
		String s_utf8 = new String(b, "utf-8");
		System.out.println(s_utf8.getBytes("utf-8").length);
		System.out.println(s_utf8.toCharArray().length);
		System.out.println(s.length());
		System.out.println(s_utf8);
		System.out.println(s);
		System.out.println(b.length);
	}

	@Test
	public void test05() {
		StackTraceElement[] sts = Thread.currentThread().getStackTrace();
		Thread.interrupted();
		for (StackTraceElement st : sts) {
			System.out.println("className: " + st.getClassName()
					+ "--fileName: " + st.getFileName() + "--line: "
					+ st.getLineNumber());
		}
		int a;
	}

	@Test
	public void test06() {
		String pk = this.getClass().getPackage().getName();
		System.out.println(pk);
		ClassLoader classLoader = getClass().getClassLoader();
		URL url = classLoader.getResource(pk);
		String urlFileString = url.getFile();
		System.out.println("urlFile: " + urlFileString);

		System.out.println("protocol: " + url.getProtocol() + " "
				+ url.toString());

		File f = new File(urlFileString);
		for (File file : f.listFiles()) {
			System.out.println(file.getAbsolutePath());

		}
	}
	
	@Test
	public void test07() {
		char a='a';
		int b = (int)a;
		System.out.println(b);
		System.out.println(Integer.MAX_VALUE); //intteger是四个字节
		System.out.println(0x7FFFFFFF);
		System.err.println(getClass().getSimpleName());
		System.out.println(getClass().getName());
	}
	
	@Test
	public void test08() {
		try {
			InetAddress[] addrs = InetAddress.getAllByName("www.jjmmw.com");
			for (InetAddress a: addrs) {
				System.out.println(a);
				System.out.println(a.getHostAddress());
			}
			
			InetAddress localHostAddress = InetAddress.getLocalHost();
			System.out.println(localHostAddress);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
