package com.lq.web01.module.security.service;

import javax.annotation.Resource;

import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.stereotype.Service;

import com.lq.web01.platform.log.LQLogger;
import com.lq.web01.platform.log.LQLoggerFactory;
import com.lq.web01.platform.service.ServiceFacade;

/**
 * Spring Security授权服务
 * @author Administrator
 *
 */
@Service
public class SpringSecurityService {
	private static final LQLogger LOG = LQLoggerFactory
			.getLogger(SpringSecurityService.class);
	@Resource(name = "filterSecurityInterceptor")
	private FilterSecurityInterceptor fileterSecurityInterceptor;
	protected ServiceFacade serviceFacade;

}
