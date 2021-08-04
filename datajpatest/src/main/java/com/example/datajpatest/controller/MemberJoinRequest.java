package com.example.datajpatest.controller;

import com.example.datajpatest.model.Member;
import lombok.Getter;

import java.beans.ConstructorProperties;

@Getter
public class MemberJoinRequest implements Member {
    private final Long id;
    private final String name;

    @ConstructorProperties({"id", "name"})
    public MemberJoinRequest(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
