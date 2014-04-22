package com.lq.web01.module.system.service;

import javax.servlet.ServletContextEvent;

import com.lq.web01.platform.log.LQLogger;
import com.lq.web01.platform.log.LQLoggerFactory;

public class SystemListener {
	private static final LQLogger LOG = LQLoggerFactory
			.getLogger(SystemListener.class);

	private static boolean running = false;
	private static String basePath;
	private static String contextPath;

	// private static RuningTime runingTime = null;
	public static String getContextPath() {
		return contextPath;
	}

	public static void contextInitialized(ServletContextEvent sce) {
		contextPath = sce.getServletContext().getContextPath();
		LOG.info("contextPath --- " + contextPath);
	}

	public static void contextDestroyed(ServletContextEvent sce) {
		System.out.println("systemListner destroyed --- ");
	}

}
