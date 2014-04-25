package com.lq.web01.module.security.service;

import java.util.Collection;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

import com.lq.web01.platform.log.LQLogger;
import com.lq.web01.platform.log.LQLoggerFactory;
import com.lq.web01.platform.util.FileUtils;

public class SecurityService {
	private static final LQLogger LOG = LQLoggerFactory
			.getLogger(SecurityService.class);

	public void checkSeq(String seq) {
		if (StringUtils.isNotBlank(seq)) {
			LOG.debug("机器码为：" + seq);
			if (valide(seq)) {
				authSuccess();
				LOG.debug("产吕已经取得合法授权");
			} else {
				LOG.debug("产品 没有取得授权");
				authFail(seq);
			}
		} else {
			LOG.debug("机器 码获取失败");
			LOG.debug("产吕没有取得授权");
			authFail(seq);
		}

	}

	private boolean valide(String seq) {
		try {
			String authCode = auth(seq);
			LOG.error("验证码为：" + authCode);
			if (StringUtils.isBlank(authCode)) {
				return false;
			}
			Collection<String> licences = FileUtils
					.getTextFileContent("/WEB-INF/classes/licences/apdplat.licence");
			for (String no : licences) {
				if (authCode.equals(no)) {
					return true;
				}
			}
		} catch (Exception e) {
			LOG.debug("安全检查出错", e);
		}
		return false;
	}

	private String auth(String machineCode) {
		String newCode = "(yang-shangchuan@qq.com)["
				+ machineCode.toUpperCase() + "](APDPlat应用级产品开发平台)";
		String code = new Md5PasswordEncoder().encodePassword(newCode, "杨尚川")
				.toUpperCase() + machineCode.length();
		return getSplitString(code);
	}

	private String getSplitString(String str) {
		return getSplitString(str, "-", 4);
	}

	private String getSplitString(String str, String split, int length) {
		
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

	private void authFail(String seq) {
		FileUtils.createAndWriteFile("/WEB-INF/lib/server", seq);
		FileUtils.createAndWriteFile("/WEB-INF/licence", seq);
	}

	private void authSuccess() {
		FileUtils.removeFile("/WEB-INF/lib/server");
		FileUtils.removeFile("/WEB-INF/licence");
	}
}
