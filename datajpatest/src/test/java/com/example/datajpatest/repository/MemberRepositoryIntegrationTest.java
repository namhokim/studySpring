package com.example.datajpatest.repository;

import com.example.datajpatest.model.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@DataJpaTest
class MemberRepositoryIntegrationTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    void createTest1() {
        // given
        final Long memberIdParam = 1L;
        final String memberNameParam = "tester 1";
        MemberJpaEntity entity = createBy(memberIdParam, memberNameParam);

        // when
        final MemberJpaEntity saved = memberRepository.save(entity);
        final MemberJpaEntity loadedById = memberRepository.findById(saved.getId()).orElseThrow();

        // then
        assertThat(loadedById.getName()).isEqualTo(memberNameParam);
    }

    @Test
    void createTest2() {
        // given
        final Long memberIdParam = 2L;
        final String memberNameParam = "tester 2";
        MemberJpaEntity entity = createBy(memberIdParam, memberNameParam);

        // when
        final MemberJpaEntity saved = memberRepository.save(entity);
        final MemberJpaEntity loadedById = memberRepository.findById(saved.getId()).orElseThrow();

        // then
        assertThat(loadedById.getName()).isEqualTo(memberNameParam);
    }

    @Test
    void createTest3() {
        // given
        final Long memberIdParam = 3L;
        final String memberNameParam = "tester 2";
        MemberJpaEntity entity = createBy(memberIdParam, memberNameParam);

        // when
        final MemberJpaEntity saved = memberRepository.save(entity);
        final MemberJpaEntity loadedById = memberRepository.findById(saved.getId()).orElseThrow();

        // then
        assertThat(loadedById.getName()).isEqualTo(memberNameParam);
    }

    private MemberJpaEntity createBy(Long memberId, String memberName) {
        Member member = mock(Member.class);
        given(member.getId()).willReturn(memberId);
        given(member.getName()).willReturn(memberName);
        return MemberJpaEntity.from(member);
    }

}
