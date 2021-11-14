package com.tistory.namocom.r2dbc.repository;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Table("MEMBER")
public class Member {
    @Id
    @Column("ID")
    private Long id;

    @Column("NAME")
    private String name;

    private Member(String name) {
        this.id = null;
        this.name = name;
    }

    private Member(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Member newMember(String name) {
        return new Member(name);
    }

    public static Member updateMember(Long id, String name) {
        return new Member(id, name);
    }
}
