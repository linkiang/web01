package com.lq.web01.platform.model;

import java.io.Serializable;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

@MappedSuperclass //不会映射到数据库表，但是它的属性会映射到子类的表中。
@EntityListeners(value = ModelListener.class)
public abstract class Model implements Serializable {

	/**
	 * 
	 */
	@Transient
	@RenderIgnore
	private static final long serialVersionUID = 1L;

}
