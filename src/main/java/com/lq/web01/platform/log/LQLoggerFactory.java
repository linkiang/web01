package com.lq.web01.platform.log;

import java.util.HashMap;
import java.util.Map;

public class LQLoggerFactory {
	private static final Map<Class, LQLogger> CACHE = new HashMap<Class, LQLogger>();

	private LQLoggerFactory() {
	}
	
	public static synchronized LQLogger getLogger(Class clazz) {
		LQLogger logger = CACHE.get(clazz);
		if (logger == null) {
			logger = new LQLoggerImpl(clazz);
			CACHE.put(clazz, logger);
		}
		return logger;
	}
}
