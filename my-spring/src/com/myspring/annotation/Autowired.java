package com.myspring.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.myspring.annotation.type.Scope;

/**
 * <p>
 * Autowired indicates that the annotated field will have instance variable.
 * <p>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
public @interface Autowired {
	public Scope scope() default Scope.SINGLETON;

	public String name() default "";
}
