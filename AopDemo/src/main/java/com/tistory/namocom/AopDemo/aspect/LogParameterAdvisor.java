package com.tistory.namocom.AopDemo.aspect;

import com.tistory.namocom.AopDemo.annotaion.LogParameter;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Method;

@Slf4j
@Aspect
@Component
public class LogParameterAdvisor {

    private static final int NOT_FOUND = -1;

    @Before("@annotation(com.tistory.namocom.AopDemo.annotaion.LogParameter)")
    public void logParameterBefore(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        LogParameter logParameter = method.getAnnotation(LogParameter.class);

        String parameterName = logParameter.parameterName();
        if (ObjectUtils.isEmpty(parameterName)) {
            return;
        }

        int argIdx = argumentIndexOf(parameterName, method, signature.getParameterNames());
        if (argIdx != NOT_FOUND) {
            Object parameterValue = joinPoint.getArgs()[argIdx];
            logParameter(parameterName, parameterValue, logParameter, method);
        }
    }


    private int argumentIndexOf(String argument, Method method, String[] sources) {
        for (int i = 0; i < sources.length; i++) {
            if (sources[i].equals(argument)) {
                return i;
            }
        }

        log.warn("Declaration of argument '{}' on @LogParameter of {} is not present.", argument, describe(method));
        return NOT_FOUND;
    }

    private void logParameter(String parameterName, Object parameterValue, LogParameter logParameter, Method method) {
        String logPrefix = logParameter.logPrefix();
        if (logParameter.printLocation()) {
            log.info("{}{}={} [{}]", logPrefix, parameterName, parameterValue.toString(), describe(method));
        } else {
            log.info("{}{}={}", logPrefix, parameterName, parameterValue.toString());
        }
    }

    // borrow from org.springframework.core.annotation.AttributeMethods.describe(java.lang.reflect.Method)
    private String describe(@Nullable Method method) {
        if (method == null) {
            return "(none)";
        }
        return describe(method.getDeclaringClass(), method.getName());
    }

    private String describe(@Nullable Class<?> annotationType, @Nullable String attributeName) {
        if (attributeName == null) {
            return "(none)";
        }
        String fullPackageClassName = (annotationType != null ? annotationType.getName() : "");
        return fullPackageClassName + "#" + attributeName;
    }

}
