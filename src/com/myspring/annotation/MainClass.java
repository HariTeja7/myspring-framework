package com.myspring.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * The type annotated with @MainClass will be primary class for testing.
 */
@Retention(RUNTIME)
@Target(TYPE)
public @interface MainClass {

}
