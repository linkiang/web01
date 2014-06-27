package com.lq.web01.platform.util;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.util.Assert;

import javassist.expr.NewArray;

import com.lq.web01.platform.log.LQLogger;
import com.lq.web01.platform.log.LQLoggerFactory;

/**
 * 反射工具类
 * 
 * @author Administrator
 * 
 */
public class ReflectionUtils {

	private static final LQLogger LOG = LQLoggerFactory
			.getLogger(ReflectionUtils.class);

	private ReflectionUtils() {
	};

	/**
	 * 搜索给定的所有的类里，某个类的所有子类或实现类
	 * 
	 * @param cls
	 * @param clazzs
	 * @return
	 */
	public static List<Class<?>> getAssignedClass(Class<?> cls,
			List<Class<?>> clazzs) {
		List<Class<?>> classes = new ArrayList<>();
		for (Class<?> c : clazzs) {
			// 判断cls是c的超类
			if (cls.isAssignableFrom(c) && !cls.equals(c)) {
				classes.add(c);
			}
		}
		return classes;
	}

	/**
	 * 获取某个类型的同一路径下的所有类
	 * 
	 * @param cls
	 * @return
	 * @throws ClassNotFoundException
	 */
	public static List<Class<?>> getClasses(Class<?> cls)
			throws ClassNotFoundException {
		String pk = cls.getPackage().getName();
		String path = pk.replace('.', '/');
		ClassLoader classLoader = Thread.currentThread()
				.getContextClassLoader();
		URL url = classLoader.getResource(path);
		return getClasses(new File(url.getFile()), pk, null);
	}

	/**
	 * 获取某个路径下的指定的Package下的所有类，不包括<code>outsides</code>中的
	 * 
	 * @param dir
	 * @param pk
	 * @param outsides
	 * @return
	 */
	private static List<Class<?>> getClasses(File dir, String pk,
			String[] outsides) {
		LOG.debug(" Dir: {}, PK: {}", new Object[] { dir, pk });
		List<Class<?>> classes = new ArrayList<>();
		if (!dir.exists()) {
			return classes;
		}
		String thisPk = StringUtils.isBlank(pk) ? "" : pk + ".";
		for (File file : dir.listFiles()) {
			if (file.isDirectory()) {
				classes.addAll(getClasses(file, thisPk, outsides));
			}
			String name = file.getName();
			if (name.endsWith(".class")) {
				Class<?> clazz = null;
				String clazzName = thisPk
						+ name.substring(0, name.length() - 6);
				LOG.debug("Class: {}", clazzName);
				if (outsides == null || outsides.length == 0
						|| !ArrayUtils.contains(outsides, clazzName)) {
					try {
						clazz = Class.forName(clazzName);
					} catch (Throwable e) {
						LOG.error("实例化失败", e);
					}
					if (clazz != null) {
						classes.add(clazz);
					}
				}
			}
		}
		return classes;
	}

	@SuppressWarnings("unchecked")
	public static <T> T cloneInstance(T instance) {
		Class<T> cls = (Class<T>) instance.getClass();
		T newins = (T) BeanUtils.instantiateClass(cls);
		BeanUtils.copyProperties(instance, newins);
		return newins;
	}

	public static List<Field> getgetDeclaredFields(final Object object) {
		Assert.notNull(object, "object不能为空");
		List<Field> fields = new ArrayList<>();
		for (Class<?> superClass = object.getClass(); superClass != Object.class; superClass = superClass
				.getSuperclass()) {
			Field[] f = superClass.getDeclaredFields();
			fields.addAll(Arrays.asList(f));
		}
		return fields;
	}

	/**
	 * 直接读取对象属性值，无视private/protected修饰符，不经过getter函数.
	 * 
	 * @param object
	 * @param fieldName
	 * @return
	 */
	public static Object getFieldValue(final Object object,
			final String fieldName) {
		try {
			Field field = getDeclaredField(object, fieldName);
			if (field == null) {
				throw new IllegalArgumentException("Could not find field ["
						+ fieldName + "] on target [" + object + "]");
			}
			return getFieldValue(object, field);
		} catch (Exception e) {
			String methodName = "get"
					+ Character.toUpperCase(fieldName.charAt(0))
					+ fieldName.substring(1);

			try {
				Method method = object.getClass().getMethod(methodName);
				return method.invoke(object);
			} catch (NoSuchMethodException | SecurityException
					| IllegalAccessException | IllegalArgumentException
					| InvocationTargetException ex) {
				LOG.error("Could not exec method [" + methodName
						+ "] on target [" + object + "]", ex);
			}
		}
		return null;
	}

	private static Object getFieldValue(final Object object, final Field field) {
		makeAccessible(field);
		Object result = null;
		try {
			result = field.get(object);
		} catch (IllegalAccessException e) {
			LOG.error("不可能抛出的异常{}", e.getMessage());
		}
		return result;
	}

	private static void makeAccessible(Field field) {
		if (!Modifier.isPublic(field.getModifiers())
				|| !Modifier.isPublic(field.getDeclaringClass().getModifiers())) {
			field.setAccessible(true);
		}
	}

	/**
	 * 循环向上转型，获取对象的DeclaredField.
	 * 
	 * @param object
	 * @param fieldName
	 * @return
	 */
	private static Field getDeclaredField(final Object object,
			final String fieldName) {
		Assert.notNull(object, "object不能为空");
		Assert.hasText(fieldName, "fieldName");
		for (Class<?> superClass = object.getClass(); superClass != Object.class; superClass = superClass
				.getSuperclass()) {
			try {
				return superClass.getDeclaredField(fieldName);
			} catch (NoSuchFieldException e) {
				// TODO: handle exception
				// 没有找到该名字的Field，会循环查找
			}
		}
		return null;
	}
}
