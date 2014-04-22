package com.lq.web01.platform.spring;

import javax.servlet.ServletContextEvent;
import org.springframework.web.context.ContextLoaderListener;
import com.lq.web01.module.system.service.SystemListener;

public class LQContextLoaderListener extends ContextLoaderListener {
	@Override
	public void contextInitialized(ServletContextEvent event) {
		SystemListener.contextInitialized(event);
		super.contextInitialized(event);
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		super.contextDestroyed(event);
		SystemListener.contextDestroyed(event);
	}
}
