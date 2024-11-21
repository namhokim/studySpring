package com.example.datajpatest.controller;

import com.example.datajpatest.model.Member;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class MemberJoinRequest implements Member {
    @NotNull
    private Long id;
    @NotBlank
    private String name;
    @NotNull
    @Pattern(regexp = "[0-9]{10}|[0-9]{3}-[0-9]{2}-[0-9]{5}", message = "10자리의 숫자 또는 대시를 포함한 12자리의 숫자만 입력가능합니다.")
    private String businessNumber;

}
