package com.myspring.bean;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.myspring.annotation.Component;
import com.myspring.annotation.Configuration;
import com.myspring.helper.ContextUtils;
import com.myspring.helper.FileScanner;
import com.myspring.helper.ReflectionUtils;

/**
 * <p>
 * The ApplicationContext is used to scan and collect all bean with component
 * and qualifier annotation.
 * </p>
 */
public class ApplicationContext {

	/** The bean container will have all beans. */
	private Map<String, Object> beanContainer = new HashMap<>();

	/** The sub class map will have sub class definition. */
	private Map<String, Set<String>> subClassmap = new HashMap<>();

	private FileScanner fileScanner = FileScanner.get();

	private ReflectionUtils reflectionUtils = ReflectionUtils.get();

	public ApplicationContext(Class<?> intializerClass) {
		fileScanner.scanAndDo(intializerClass, this::addToBeanMap);
	}

	/**
	 * Gets the bean map.
	 *
	 * @return the bean map
	 */
	public Map<String, Object> getBeanMap() {
		return beanContainer;
	}

	/**
	 * Gets the bean set.
	 *
	 * @return the bean set
	 */
	public Map<String, Set<String>> getBeanSet() {
		return subClassmap;
	}

	/**
	 * Adds the to bean map.
	 *
	 * @param <T>   the generic type
	 * @param clazz the clazz
	 */
	private <T> void addToBeanMap(Class<T> clazz) {
		if (!clazz.isInterface() && !Modifier.isAbstract(clazz.getModifiers())) {
			if (clazz.isAnnotationPresent(Component.class)) {
				addComponentBean(clazz);
			} else if (clazz.isAnnotationPresent(Configuration.class)) {
				addConfigurationBean(clazz);
			}
		}
	}

	/**
	 * Adds the component bean to bean container
	 *
	 * @param <T>   the generic type
	 * @param clazz the clazz
	 */
	private <T> void addComponentBean(Class<T> clazz) {
		Object object = reflectionUtils.getNewObjectofClass(clazz);
		String beanName = ContextUtils.getBeanName(clazz);
		beanContainer.put(beanName, object);
		for (Class<?> superClass : clazz.getInterfaces()) {
			if (!subClassmap.containsKey(superClass.getSimpleName())) {
				subClassmap.put(superClass.getSimpleName(), new HashSet<>());
			}
			subClassmap.get(superClass.getSimpleName()).add(beanName);
		}
	}

	/**
	 * Adds the configuration bean to bean container
	 *
	 * @param <T>   the generic type
	 * @param clazz the clazz
	 */
	private <T> void addConfigurationBean(Class<T> clazz) {
		String configurationName = ContextUtils.getBeanName(clazz);
		beanContainer.put(configurationName, reflectionUtils.getInstanceOfClass(clazz));
		for (Method method : clazz.getDeclaredMethods()) {
			String beanName = ContextUtils.getBeanName(method);
			beanContainer.put(beanName,
					reflectionUtils.invokeMethod(clazz, method.getName(), beanContainer.get(configurationName)));
			Class<?> returnType = method.getReturnType();
			if (!subClassmap.containsKey(returnType.getSimpleName())) {
				subClassmap.put(returnType.getSimpleName(), new HashSet<>());
			}
			subClassmap.get(returnType.getSimpleName()).add(beanName);
		}
	}

}