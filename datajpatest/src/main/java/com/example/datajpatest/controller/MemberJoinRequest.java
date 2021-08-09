package com.example.datajpatest.controller;

import com.example.datajpatest.model.Member;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class MemberJoinRequest implements Member {
    @NotNull
    private Long id;
    @NotBlank
    private String name;
    @Length(min = 10, max = 12)
    private String businessNumber;

}
