package com.myspring.annotation.type;

/**
 * <p>
 * The Enum Scope is used with autowire. SINGLETON will inject same bean
 * everytime. PROTOTYPE will inject new instance.
 * </p>
 */
public enum Scope {

	PROTOTYPE, SINGLETON;

}
