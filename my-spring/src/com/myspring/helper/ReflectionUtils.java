package com.myspring.helper;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ReflectionUtils {

	private static ReflectionUtils reflectionUtils = null;

	public static ReflectionUtils get() {
		if (reflectionUtils == null) {
			reflectionUtils = new ReflectionUtils();
		}
		return reflectionUtils;
	}

	public Class<?> getClassFromPackage(File file) {
		String classPath = file.getAbsolutePath();
		classPath = classPath.substring(classPath.indexOf("src") + 4, classPath.length()).replace("\\", ".")
				.replace(".java", "");
		return getClassFromPath(classPath);
	}

	public Class<?> getClassFromPath(String classPath) {
		Class<?> clazz = null;
		try {
			clazz = Class.forName(classPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return clazz;
	}

	public Object getNewObjectofClass(Class<?> clazz) {
		Object object = null;
		try {
			if (!clazz.isInterface() || !Modifier.isAbstract(clazz.getModifiers()))
				object = getInstanceOfClass(clazz);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return object;
	}

	public <T> T getInstanceOfClass(Class<T> clazz) {
		T object = null;
		try {
			object = clazz.getConstructor(null).newInstance(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return object;
	}

	@SuppressWarnings("unchecked")
	public <T> T invokeMethod(Class<T> clazz, String methodName, Object instance) {
		T t = null;
		try {
			Method method = clazz.getDeclaredMethod(methodName);
			method.setAccessible(true);
			t = (T) method.invoke(instance);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}

	public void setField(Object classInstance, Field field, Object object) {
		try {
			field.setAccessible(true);
			field.set(classInstance, object);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
