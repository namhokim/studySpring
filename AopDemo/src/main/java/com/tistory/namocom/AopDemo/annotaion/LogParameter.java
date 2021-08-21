package com.tistory.namocom.AopDemo.annotaion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface LogParameter {
    String logPrefix() default "";

    String parameterName() default "";

    boolean printLocation() default false;
}