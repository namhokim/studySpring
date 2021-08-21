package com.tistory.namocom.AopDemo.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LogReturnValueAdvisor {

    @AfterReturning(pointcut = "@annotation(com.tistory.namocom.AopDemo.annotaion.LogParameter)", returning = "returnObject")
    public void logReturnValueAfter(String returnObject) {
        log.info("Return value: {}", returnObject);
    }

}
