package com.tistory.namocom.AopDemo.controller;

import com.tistory.namocom.AopDemo.annotaion.LogParameter;
import com.tistory.namocom.AopDemo.annotaion.LogReturnValue;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Hello {

//    @LogParameter(logPrefix = "@RequestParam ", parameterName = "name", printLocation = true)
    @LogParameter("name")
    @LogReturnValue
    @GetMapping("/")
    public String hello(@RequestParam String name) {
        if (StringUtils.hasText(name)) {
            return "Hello " + name;
        }
        return "World";
    }

}
