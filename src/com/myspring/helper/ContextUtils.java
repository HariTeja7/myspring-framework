package com.myspring.helper;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.myspring.annotation.Autowired;
import com.myspring.annotation.Bean;
import com.myspring.annotation.Qualifier;

public class ContextUtils {

	private ContextUtils() {

	}

	public static String getBeanName(Class<?> clazz) {
		String beanName = StringUtils.toLowerCase(clazz.getSimpleName(), 0, 1);
		if (clazz.isAnnotationPresent(Qualifier.class)) {
			Qualifier qualifier = clazz.getAnnotation(Qualifier.class);
			if (!qualifier.name().isBlank() && !qualifier.name().isEmpty()) {
				beanName = qualifier.name();
			}
		}
		return beanName;
	}

	public static String getBeanName(Method method) {
		String beanName = method.getName();
		if (method.isAnnotationPresent(Bean.class)) {
			Bean bean = method.getAnnotation(Bean.class);
			if (!bean.name().isBlank() && !bean.name().isEmpty()) {
				beanName = bean.name();
			}
		}
		return beanName;
	}

	public static String getFieldName(Field field) {
		String fieldName = field.getName();
		if (field.isAnnotationPresent(Autowired.class)) {
			Autowired autowired = field.getAnnotation(Autowired.class);
			if (!autowired.name().isBlank() && !autowired.name().isEmpty()) {
				fieldName = autowired.name();
			}
		}
		return fieldName;
	}

}
