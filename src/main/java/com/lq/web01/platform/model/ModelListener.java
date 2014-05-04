package com.lq.web01.platform.model;

import java.util.LinkedList;
import java.util.List;

import com.lq.web01.platform.log.LQLogger;
import com.lq.web01.platform.log.LQLoggerFactory;
import com.lq.web01.platform.model.handler.ModelHandler;

public class ModelListener {
	private static final LQLogger LOG = LQLoggerFactory
			.getLogger(ModelListener.class);
	private static final List<ModelHandler> modelHandlers = new LinkedList<>();
}
