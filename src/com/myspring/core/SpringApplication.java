package com.myspring.core;

import java.lang.reflect.Method;
import java.util.Objects;

import com.myspring.annotation.Main;
import com.myspring.annotation.MainClass;
import com.myspring.bean.DependencyInjector;
import com.myspring.bean.ApplicationContext;
import com.myspring.helper.ContextUtils;
import com.myspring.helper.FileScanner;
import com.myspring.helper.ReflectionUtils;

/**
 * <p>
 * The SpringApplication is the runner. It intiates the application.
 * </p>
 */
public class SpringApplication {

	private SpringApplication() {

	}

	/**
	 * Run is used to intialize the application
	 *
	 * @param primarySource the primary source
	 */
	public static void run(Class<?> primarySource) {
		ApplicationContext context = new ApplicationContext(primarySource);
		DependencyInjector.initialize(primarySource, context);
		FileScanner.get().scanAndDo(primarySource, e -> invokeMain(e, context));
	}

	public static void invokeMain(Class<?> clazz, ApplicationContext context) {
		if (clazz.isAnnotationPresent(MainClass.class)) {
			for (Method method : clazz.getDeclaredMethods()) {
				if (method.isAnnotationPresent(Main.class)) {
					Object instanceObject = context.getBeanMap().get(ContextUtils.getBeanName(clazz));
					if (Objects.isNull(instanceObject)) {
						instanceObject = ReflectionUtils.get().getNewObjectofClass(clazz);
					}
					ReflectionUtils.get().invokeMethod(clazz, method.getName(), instanceObject);
				}
			}
		}
	}

}
