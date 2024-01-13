package com.myspring.bean;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;

import com.myspring.annotation.Autowired;
import com.myspring.annotation.Component;
import com.myspring.annotation.type.Scope;
import com.myspring.helper.ContextUtils;
import com.myspring.helper.FileScanner;
import com.myspring.helper.ReflectionUtils;

/**
 * <p>
 * DependencyInjector will inject all beans collected from Application context.
 * It will inject beans to fields with autowired annotations
 * </p>
 */
public class DependencyInjector {

	private FileScanner fileScanner = FileScanner.get();

	private ReflectionUtils reflectionUtils = ReflectionUtils.get();

	private DependencyInjector(Class<?> initializerClazz, ApplicationContext beanScanner) {
		fileScanner.scanAndDo(initializerClazz,
				e -> scanAndInjectBean(e, beanScanner.getBeanMap(), beanScanner.getBeanSet()));
	}

	public static DependencyInjector initialize(Class<?> initializerClazz, ApplicationContext beanScanner) {
		return new DependencyInjector(initializerClazz, beanScanner);
	}

	private void scanAndInjectBean(Class<?> clazz, Map<String, Object> beanMap, Map<String, Set<String>> beanSet) {
		if (clazz.isAnnotationPresent(Component.class)) {
			String className = ContextUtils.getBeanName(clazz);
			for (Field field : clazz.getDeclaredFields()) {
				if (field.isAnnotationPresent(Autowired.class)) {
					injectBean(beanMap, beanSet, className, field);
				}
			}
		}
	}

	private void injectBean(Map<String, Object> beanMap, Map<String, Set<String>> beanSet, String className,
			Field field) {
		Scope scope = field.getAnnotation(Autowired.class).scope();
		String beanName = ContextUtils.getFieldName(field);
		if (beanMap.containsKey(beanName)) {
			setObjectByScope(scope, field, className, beanName, beanMap);
		} else if (field.getType().isInterface() && beanSet.containsKey(field.getType().getSimpleName())) {
			if (beanSet.get(field.getType().getSimpleName()).size() > 1) {
				System.out.println("More Beans found...");
			} else {
				beanName = beanSet.get(field.getType().getSimpleName()).iterator().next();
				setObjectByScope(scope, field, className, beanName, beanMap);
			}
		}
	}

	private void setObjectByScope(Scope scope, Field field, String className, String beanName,
			Map<String, Object> beanMap) {
		Object object = null;
		switch (scope) {
		case SINGLETON:
			object = beanMap.get(beanName);
			break;
		case PROTOTYPE:
			object = reflectionUtils.getNewObjectofClass(beanMap.get(beanName).getClass());
			break;
		default:
			break;
		}
		reflectionUtils.setField(beanMap.get(className), field, object);
	}

}
