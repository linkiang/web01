package com.lq.web01.platform.service;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.management.Query;
import javax.naming.directory.SearchControls;

import org.hibernate.sql.Delete;
import org.hibernate.sql.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.lq.web01.platform.log.LQLogger;
import com.lq.web01.platform.log.LQLoggerFactory;
import com.lq.web01.platform.model.Model;

@Service
public class ServiceFacade {

	protected final LQLogger LOG = LQLoggerFactory.getLogger(getClass());

	@Resource(name = "daoFacade")
	private DaoFacade dao = null;

	public void setDao(DaoFacade dao) {
		this.dao = dao;
	}

	public void clear() {
		dao.clear();
	}

	// 批量保存，批量提交，显著提升性能
	@Transactional
	public <T extends Model> void create(List<T> models) {
		for (T model : models) {
			dao.create(model);
		}
	}

	@Transactional
	public <T extends Model> void create(T models) {
		dao.create(model);
	}
	
	public <T extends Model> T retrieve(Class<T> modelClass, Integer modelId) {
		T model = dao.retrieve(modelClass, modelId);
		if (model == null) {
			return null;
		}
		return model;
	}
	
	@Transactional
	public <T extends Model> void update(T model) {
		dao.update(model);
	}
	
	@Transactional
	public <T extends Model> void Update(Class<T> modelClass, Integer modelId, List<Property> properties) {
		dao.update(modelClass, modelId, properties);
	}
	
	@Transactional 
	public <T extends Model> void delete(Class<T> modelClass, Integer modelId) {
		dao.delete(modelClass, modelId);
	}
	
	@Transactional
	public <T extends Model> List<Integer> delete(Class<T> modelClass , Integer[] modelIds) {
		List<Integer> ids = new ArrayList<>();
		for (Integer modelId : modelIds) {
			try {
				this.delete(modelClass, modelId);
				ids.add(modelId);
			} catch (Exception e) {
				LOG.error("删除模型出错", e);
			}
		}
		return ids;
	}
	
	public <T extends Model> Page<T> query(Class<T> modelClass) {
		Page<T> page = dao.query(modelClass, null);
		return page;
	}
	
	public <T extends Model> Page<T> query(Class<T> modelClass, PageCriteria pageCriteria) {
		Page<T> page = dao.query(modelClass, pageCriteria, null);
		return page;
	}
	
	public <T extends Model> Page<T> query(Class<T> modelClass, PageCriteria pageCriteria, PropertyCriteria propertyCriteria) {
		Page<T> page = dao.query(modelClass, pageCriteria, propertyCriteria);
		return page;
	}
	
	public <T extends Model> Page<T> Query(Class<T> modelClass, PageCriteria pageCriteria, PropertyCriteria propertyCriteria, OrderCriteria orderCriteria) {
		Page<T> page = dao.query(modelClass, pageCriteria, propertyCriteria, orderCriteria);
		return page;
	}
	
	@Transactional
	public <T extends Model> Page<T> search(String queryString, PageCriteria pageCriteria, Class<T> modelClass) {
		Page<T> page = dao.search(queryString, pageCriteria, modelClass);
		return page;
	}

}
