package com.myspring.annotation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * <p>
 * The Main is annotated on method in MainClass, This is used for testing. The
 * annotated method is void and no arg method
 * </p>
 */
@Retention(RUNTIME)
@Target(ElementType.METHOD)
public @interface Main {

}
