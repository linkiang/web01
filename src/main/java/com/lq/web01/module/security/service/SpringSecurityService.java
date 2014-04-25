package com.lq.web01.module.security.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lq.web01.platform.log.LQLogger;
import com.lq.web01.platform.log.LQLoggerFactory;

/**
 * Spring Securityr授权服务
 * @author Administrator
 *
 */
@Service
public class SpringSecurityService {
	private static final LQLogger LOG = LQLoggerFactory
			.getLogger(SpringSecurityService.class);
	@Resource(name = "filterSecurityInterceptor")
	private FilterSecurityInterceptor fileterSecurityInterceptor;

}
