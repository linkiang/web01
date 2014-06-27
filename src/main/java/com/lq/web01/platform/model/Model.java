package com.lq.web01.platform.model;

import java.io.File;
import java.io.Serializable;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlTransient;

import org.apache.commons.lang3.Validate;
import org.compass.annotations.SearchableId;
import org.compass.annotations.SearchableProperty;

import com.lq.web01.platform.annotation.ModelAttr;
import com.lq.web01.platform.annotation.ModelAttrRef;
import com.lq.web01.platform.annotation.ModelCollRef;
import com.lq.web01.platform.annotation.RenderIgnore;
import com.lq.web01.platform.log.LQLogger;
import com.lq.web01.platform.log.LQLoggerFactory;
import com.lq.web01.platform.util.ReflectionUtils;

@MappedSuperclass //不会映射到数据库表，但是它的属性会映射到子类的表中。
@EntityListeners(value = ModelListener.class)
public abstract class Model implements Serializable {

	/**
	 * 
	 */
	@Transient
	@RenderIgnore
	private static final long serialVersionUID = 1L;
	
	@Transient
	@RenderIgnore
	protected final LQLogger LOG = LQLoggerFactory.getLogger(getClass());
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@SearchableId
	@ModelAttr("编号")
	protected Integer id;
	
	@SearchableProperty(format = "yyyy-MM-dd")
	@Column(updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@ModelAttr("创建时间")
	protected Date createTime;
	
	@SearchableProperty(format = "yyyy-MM-dd")
	@Column(insertable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@ModelAttr("上一次更新时间")
	protected Date updateTime;
	
	@Version
	@ModelAttr("更新次数")
	protected Integer version;
	
	public Model() {
		ModelMetaData.addMetaData(this);		
	}
	
	// 得到该类需要搜索的字段列表 (运行时获取)
	public List<String> getSearchProperties() {
		List<String> list = new ArrayList<>();
		//获取所有字段，包括继承的
		List<Field> fields = ReflectionUtils.getgetDeclaredFields(this);
		for (Field field : fields) {
			if (field.isAnnotationPresent(SearchableProperty.class)) {
				String fieldName = field.getName();
				list.add(fieldName);
			}
		}
		return list;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@XmlTransient
	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Model)) {
			return false;
		}
		Model model = (Model)obj;
		return model.getId() == this.getId();
	}
	
	@Override
	public int hashCode() {
		if (id == null) {
			id = -1;
		}
		return Integer.valueOf(id + 1000).hashCode();
	}
	
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append("【").append(getMetaData()).append("】").append("\n");
		if (id != null) {
			result.append("id: ").append(id).append("\n");
		}
		Field[] fields = this.getClass().getDeclaredFields();
		int len = fields.length;
		for (int i = 0; i < len; i++) {
			Field field = fields[i];
			String fieldName = field.getName();
			try {
				field.setAccessible(true);
				String fieldAttr;
				if (field.isAnnotationPresent(Lob.class)) {
					LOG.debug("字段[" + fieldName + "]为大对象，在toString()方法中忽略输出");
					continue;
				}
				if (field.isAnnotationPresent(ModelAttr.class)) {
					ModelAttr attr = field.getAnnotation(ModelAttr.class);
					fieldAttr = attr.value();
				} else {
					LOG.debug("字段[" + fieldName + "]未加@ModelAttr注解，在toString()方法中忽略输出");
					continue;
				}
				// 获取该字段的值
				Object value = field.get(this);
				if (value == null) {
					LOG.debug("忽略空字段: " + fieldName);
					continue;
				}
				//字段值处理
				String valueClass = value.getClass().getSimpleName();
				LOG.debug("fieldAttr: " + fieldAttr + " fieldName: " + fieldName + " valueClass: " + valueClass);
				//处理集合类型　
				if (field.isAnnotationPresent(ModelCollRef.class)) {
					ModelCollRef ref = field.getAnnotation(ModelCollRef.class);
					String fieldRef = ref.value();//value指明了集合元素中需要打印出来的那个字段是什么。
					Collection collection = (Collection)value;
					LOG.debug("处理集合，[字段为：" + fieldName + ",大小为：" + collection.size());
					if (collection.size() > 0) {
						StringBuilder str = new StringBuilder();
						for (Object object : collection) {
							str.append(ReflectionUtils.getFieldValue(object, fieldRef).toString()).append(",");
						}
						str = str.deleteCharAt(str.length()-1);
						value = str.toString();
					}
				}
				//处理复杂对象类型
				if (field.isAnnotationPresent(ModelAttrRef.class)) {
					LOG.debug("处理对象，字段为: " + fieldName);
					ModelAttrRef ref = field.getAnnotation(ModelAttrRef.class);
					String fieldRef = ref.value();
					//获取fieldRef的值
					value = ReflectionUtils.getFieldValue(value, fieldRef);
				}
				if ("TimeStamp".equals(valueClass) || "Date".equals(valueClass)) {
					
				}
			} catch (Exception e) {
			}
		}
	}
	
	public abstract String getMetaData();

}
