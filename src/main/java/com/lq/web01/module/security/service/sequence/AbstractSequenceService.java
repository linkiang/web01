package com.lq.web01.module.security.service.sequence;

import java.io.File;
import java.security.MessageDigest;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import org.hyperic.sigar.Mem;
import org.hyperic.sigar.NetFlags;
import org.hyperic.sigar.NetInterfaceConfig;
import org.hyperic.sigar.Sigar;

import com.lq.web01.platform.log.LQLogger;
import com.lq.web01.platform.log.LQLoggerFactory;
import com.lq.web01.platform.util.ConverterUtils;
import com.lq.web01.platform.util.FileUtils;

public abstract class AbstractSequenceService implements SequenceService {
	protected final LQLogger LOG = LQLoggerFactory.getLogger(getClass());

	protected String getMD5(String message) {
		message += "{apdplat}";
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			LOG.debug("MD5摘要长度：" + md.getDigestLength());
			byte[] b = md.digest(message.getBytes("utf-8"));
			String md5 = ConverterUtils.byte2HexString(b) + message.length();
			return getSplitString(md5);
		} catch (Exception e) {
			LOG.error("MD5摘要失败", e);
		}
		return null;
	}

	protected String getSplitString(String str) {
		return getSplitString(str, "-", 4);
	}

	protected String getSplitString(String str, String split, int length) {
		int len = str.length();
		StringBuilder temp = new StringBuilder();
		for (int i = 0; i < len; i++) {
			if (i % length == 0 && i > 0) {
				temp.append(split);
			}
			temp.append(str.charAt(i));
		}
		String[] attrs = temp.toString().split(split);
		StringBuilder finalMachineCode = new StringBuilder();
		for (String attr : attrs) {
			if (attr.length() == length) {
				finalMachineCode.append(attr).append(split);
			}
		}
		String result = finalMachineCode.toString().substring(0,
				finalMachineCode.toString().length() - 1);
		return result;
	}

	protected String getSigarFile(String osName) {
		StringBuilder result = new StringBuilder();
		result.append("libsigar-");
		String osArch = System.getProperty("os.arch");
		if (osArch.contains("64")) {
			result.append("amd64-");
		} else {
			result.append("x86-");
		}
		result.append(osName).append("-1.6.4.so");
		return result.toString();
	}

	protected String getSigarSequence(String osName) {
		try {
			File libFile = new File(FileUtils.getAbsolutePath("/WEB-INF/lib/"
					+ getSigarFile(osName)));
			LOG.debug("libsigar: " + libFile.getAbsolutePath());

			System.load(libFile.getAbsolutePath());
			Set<String> result = new HashSet<>();
			Sigar sigar = new Sigar();
			String[] ifaces = sigar.getNetInterfaceList();
			for (String iface : ifaces) {
				NetInterfaceConfig cfg = sigar.getNetInterfaceConfig(iface);
				if (NetFlags.LOOPBACK_ADDRESS.equals(cfg.getAddress())
						|| (cfg.getFlags() & NetFlags.IFF_LOOPBACK) != 0
						|| NetFlags.NULL_HWADDR.equals(cfg.getHwaddr())) {
					continue;
				}
				String mac = cfg.getHwaddr();
				result.add(mac);
				LOG.debug("mac: " + mac);
			}
			if (result.size() < 1) {
				return null;
			}
			Properties props = System.getProperties();
			String javaVersion = props.getProperty("java.version");
			result.add(javaVersion);
			LOG.debug("Java的运行环境版本：    " + javaVersion);
			String javaVMVersion = props.getProperty("java.vm.version");
			result.add(javaVMVersion);
			LOG.debug("Java的虚拟机实现版本：    "
					+ props.getProperty("java.vm.version"));
			String osVersion = props.getProperty("os.version");
			result.add(osVersion);
			LOG.debug("操作系统的版本：    " + props.getProperty("os.version"));

			Mem mem = sigar.getMem();
			// 内存总量
			String totalMem = mem.getTotal() / 1024L + "K av";
			LOG.debug("内存总量:    " + totalMem);
			result.add(totalMem);

			LOG.debug("result:    " + result);
			String machineCode = getMD5(result.toString());

			return machineCode;
		} catch (Throwable ex) {
			LOG.error("生成 " + osName + " 平台下的机器码失败", ex);
		}
		return null;
	}
}
