package org.xblackcat.euler.ann;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * String template to show result. {0} - result, {1}, {2}, ... - first, second and etc. arg of task
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ResultDescription {
    String value();
}
