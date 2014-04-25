package com.lq.web01.module.security.service;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.security.SecureRandom;

import javax.annotation.PostConstruct;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.springframework.util.ReflectionUtils;

import com.lq.web01.module.security.service.sequence.WindowsSequenceService;
import com.lq.web01.platform.log.LQLogger;
import com.lq.web01.platform.log.LQLoggerFactory;
import com.lq.web01.platform.util.FileUtils;

public class SecurityCheck {
	private static final LQLogger LOG = LQLoggerFactory
			.getLogger(SecurityCheck.class);
	private static final String securityKeyName;
	private static final String securityClspath;
	private static final String sequenceKeyName;
	private static final String sequenceClspath;
	private static String osName = "Windows";
	static {
		if (System.getProperty("os.name").toLowerCase().indexOf("linux") != -1) {
			osName = "Linux";
		} else if (System.getProperty("os.name").toLowerCase().indexOf("Mac") != -1) {
			osName = "Mac";
		} else if (System.getProperty("os.name").toLowerCase()
				.indexOf("solaris") != -1) {
			osName = "Solaris";
		}
		LOG.debug("osName: " + osName);
		sequenceKeyName = "/com/lq/web01/module/security/service/sequence/SequenceKey";
		sequenceClspath = "/com/lq/web01/module/security/service/sequence/"
				+ osName + "SequenceService";
		securityKeyName = "/com/lq/web01/module/security/service/SecurityKey";
		securityClspath = "/com/lq/web01/module/security/service/SecurityService";
	}

	@PostConstruct
	public static void check() {
		LOG.debug("开始进行安全检查");
		String seq = "";
		try {
			seq = getSequence();
			LOG.debug("机器指纹：" + seq);
		} catch (Exception e) {
			LOG.debug("安全检查失败", e);
		}
		try {
			/***
			 * 字节文件里对应的包名不对，这里先用不加密的类吧。
			Class clazz = loadClass(securityKeyName, securityClspath,
					"com.lq.web01.module.security.service.SecurityService");
			Method method = ReflectionUtils.findMethod(clazz, "checkSeq",
					String.class);
			Object obj = clazz.newInstance();
			method.invoke(obj, seq);
			*/
			SecurityService securityService = new SecurityService();
			securityService.checkSeq(seq);
		} catch (Exception e) {
			LOG.debug("安全检查出错", e);
		}
	}

	private static String getSequence() {
		try {
			/**
			
			Class clazz = loadClass(sequenceKeyName, sequenceClspath,
					"com.lq.web01.module.security.service.sequence." + osName
							+ "SequenceService");
			Object obj = clazz.newInstance();
			Method method = ReflectionUtils.findMethod(clazz, "getSequence");
			String seq = method.invoke(obj).toString();
			*/
			String seq = new WindowsSequenceService().getSequence();
			return seq;
		} catch (Exception e) {
			LOG.debug("获取机器码钥失败", e);
		}
		return "";
	}

	private static <T> T loadClass(String keyFile, String classFile,
			final String className) {
		try {
			// DES算法
			SecureRandom secureRandom = new SecureRandom();
			byte[] rawKeyData;
			try (InputStream fi = SecurityCheck.class
					.getResourceAsStream(keyFile)) {
				rawKeyData = FileUtils.readAll(fi);
			}
			DESKeySpec dks = new DESKeySpec(rawKeyData);
			SecretKey secretKey = SecretKeyFactory.getInstance("DES")
					.generateSecret(dks);
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.DECRYPT_MODE, secretKey, secureRandom);

			byte[] encryptedData;
			try (InputStream fi2 = SecurityCheck.class
					.getResourceAsStream(classFile)) {
				encryptedData = FileUtils.readAll(fi2);
			}

			// 将解密的class文件加载
			final byte decrytedData[] = cipher.doFinal(encryptedData);
			Object obj = new ClassLoader() {
				@Override
				public Class<?> loadClass(String name)
						throws ClassNotFoundException {
					if (className.equals(name)) {
						return super.defineClass(name, decrytedData, 0,
								decrytedData.length);
					}
					return SecurityCheck.class.getClassLoader().loadClass(name);
				}
			}.loadClass(className);
		} catch (Exception e) {
			LOG.debug("加载类失败", e);
		}
		return null;
	}

}
