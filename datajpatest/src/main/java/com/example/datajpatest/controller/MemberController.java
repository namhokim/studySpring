package com.example.datajpatest.controller;

import com.example.datajpatest.exception.MemberNotFoundException;
import com.example.datajpatest.repository.MemberJpaEntity;
import com.example.datajpatest.repository.MemberRepository;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class MemberController {

    private final MemberRepository memberRepository;

    public MemberController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @PostMapping("/member")
    public ResponseEntity<Void> joinMember(@RequestBody MemberJoinRequest request) {
        MemberJpaEntity entity = MemberJpaEntity.from(request);
        MemberJpaEntity saved = memberRepository.save(entity);
        final Long memberId = saved.getId();
        Link link = linkTo(methodOn(MemberController.class).findMember(memberId)).withSelfRel();
        return ResponseEntity.created(URI.create(link.getHref())).build();
    }

    @GetMapping("/member/{memberId}")
    public MemberResponse findMember(@PathVariable Long memberId) {
        final MemberJpaEntity jpaEntity = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException(memberId));
        return new MemberResponse(jpaEntity);
    }

}
