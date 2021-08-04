package com.example.datajpatest.repository;

import com.example.datajpatest.model.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "MEMBER", indexes = @Index(name = "MEMBER_NAME_IDX_UK", columnList = "name", unique = true))
public class MemberJpaEntity implements Member {

    @Id
    @Column(name = "ID")
    private Long id;

    @NotNull
    @Column(name = "NAME", nullable = false)
    private String name;


    private MemberJpaEntity(Member member) {
        this.id = member.getId();
        this.name = member.getName();
    }

    public static MemberJpaEntity from(Member member) {
        return new MemberJpaEntity(member);
    }
}
